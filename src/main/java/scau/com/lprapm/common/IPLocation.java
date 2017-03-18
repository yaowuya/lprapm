package scau.com.lprapm.common;

/**
 * Created by Administrator on 2017/3/18.
 */

import com.github.tsohr.JSONException;
import com.github.tsohr.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * java根据url获取json对象
 *
 * @author openks
 * @since 2013-7-16
 * 需要添加java-json.jar才能运行
 */
public class IPLocation {
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

    public static void main(String[] args) throws IOException, JSONException {
        //这里调用百度的ip定位api服务 详见 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
        String ip = AddressUtils.getPublicIP();
        String url = "http://api.map.baidu.com/location/ip?ak=4DXVpAggWWfeCYqvLCtccWsYaP1ise7B&ip=" + ip + "&coor=bd09ll";
//        String url="http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=23.12004910,113.30764968&output=json&pois=1&ak=4DXVpAggWWfeCYqvLCtccWsYaP1ise7B";
        //String url = "http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip=139.214.253.85&coor=bd09ll"
        JSONObject json = readJsonFromUrl(url);
        System.out.println(json.toString());
        System.out.println("经度：" + ((JSONObject) json.get("content")).getJSONObject("point").get("x"));
        System.out.println("维度：" + ((JSONObject) json.get("content")).getJSONObject("point").get("y"));
        String city = (String) ((JSONObject) json.get("content")).getJSONObject("address_detail").get("city");
        city = city.replace("市", "");
        System.out.println(city);
    }
}