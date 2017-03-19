package scau.com.lprapm.common;

import com.github.tsohr.JSONException;
import com.github.tsohr.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/18.
 */
public class IpAddress {

    /**
     * 得到本机的外网ip，出现异常时返回空串""
     *
     * @return
     */
    public static String getPublicIP() {
        String ip = "";

        org.jsoup.nodes.Document doc = null;
        Connection con = null;

        con = Jsoup.connect("http://www.ip138.com/ip2city.asp").timeout(10000);

        try {
            doc = con.get();

            // 获得包含本机ip的文本串：您的IP是：[xxx.xxx.xxx.xxx]
            org.jsoup.select.Elements els = doc.body().select("center");
            for (org.jsoup.nodes.Element el : els) {
                ip = el.text();
            }

            // 从文本串过滤出ip，用正则表达式将非数字和.替换成空串""
            ip = ip.replaceAll("[^0-9.]", "");
        } catch (IOException e) {
            e.printStackTrace();
            return ip;
        }
        return ip;
    }

    /**
     * @param urlStr   请求的地址
     * @param content  请求的参数 格式为：name=xxx&pwd=xxx
     * @param encoding 服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒，如果运行时出现超时，可自行增大超时时间，如加到10000
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
            // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
        }
    }

    public static Map<String, Object> getAddress() throws IOException {
        Map<String, Object> map = new LinkedHashMap<>();
        String ip = getPublicIP();
        String url = "http://api.map.baidu.com/location/ip?ak=4DXVpAggWWfeCYqvLCtccWsYaP1ise7B&ip=" + ip + "&coor=bd09ll";
        JSONObject json = readJsonFromUrl(url);
        //纬度坐标
        String lat = (String) ((JSONObject) json.get("content")).getJSONObject("point").get("y");
        //经度左边
        String lng = (String) ((JSONObject) json.get("content")).getJSONObject("point").get("x");
//        通过经纬度获取地址
        String urlStr = "http://api.map.baidu.com/geocoder/v2/?location=" + lat + "," + lng + "&output=json&pois=1&ak=4DXVpAggWWfeCYqvLCtccWsYaP1ise7B";
        String returnStr = getResult(urlStr, "ip=" + ip, "utf-8");
        JSONObject jsStr = new JSONObject(returnStr);
        JSONObject result = jsStr.getJSONObject("result");
        JSONObject addressComponent = result.getJSONObject("addressComponent");
        map.put("province", addressComponent.getString("province"));
        map.put("city", addressComponent.getString("city"));
        map.put("area", addressComponent.getString("district"));
        map.put("street", addressComponent.getString("street"));
        return map;
    }

//    public static void main(String[] args) throws IOException {
//        Map<String, Object> map = getAddress();
//        System.out.println(map);
//    }
}
