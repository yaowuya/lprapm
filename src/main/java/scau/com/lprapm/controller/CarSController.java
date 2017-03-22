package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.BestLoading;
import scau.com.lprapm.common.Constant;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.CarNeed;
import scau.com.lprapm.entity.CarPlan;
import scau.com.lprapm.entity.User;
import scau.com.lprapm.service.inter.CarSService;
import scau.com.lprapm.service.inter.OrdersService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/19.
 */
@Controller
@RequestMapping("/carscheme")
public class CarSController extends BaseController {
    @Autowired
    CarSService carSService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    OrdersService ordersService;

    public static int k = 0;
    @ResponseBody
    @RequestMapping("searchAddress")
    public JsonResult searchAddress() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> map = (Map) request.getSession().getAttribute(Constant.CURRENR_ADDR);
            String str = "";
            str = str + map.get("province") + ",";
            str = str + map.get("city") + ",";
            str = str + map.get("area") + ",";
            str = str + map.get("street");
            jsonResult = new JsonResult(true, "查询地址成功", str);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询地址失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("searchCarS")
    public JsonResult searchCarS() {
        JsonResult jsonResult = null;
        try {
            User current = (User) request.getSession().getAttribute(Constant.CURRENR_USER);
            Map<String, Object> map = (Map) request.getSession().getAttribute(Constant.CURRENR_ADDR);
            int userId = current.getUserId();
            Map<String, Object> params = super.getParamMap();
            params.put("userId", userId);
            params.put("province", map.get("province"));
            params.put("city", map.get("city"));
            params.put("area", map.get("area"));
            List<Map<String, Object>> List = carSService.searchCarS(params);
            jsonResult = new JsonResult(true, "查询物流订单成功", List);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询物流订单失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("updateCarS")
    public JsonResult updateCarS(CarPlan carPlan, CarNeed carNeed) {
        JsonResult jsonResult = null;
        try {
            carSService.updateCarS(carPlan, carNeed);
            jsonResult = new JsonResult(true, "配车成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "配车失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("searchPOS")
    public JsonResult searchPOS() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = carSService.searchPOS(params);
            jsonResult = new JsonResult(true, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("surePOS")
    public JsonResult surePOS() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            carSService.surePOS(params);
            jsonResult = new JsonResult(true, "出车成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "出车失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("caculateCar")
    public JsonResult caculateCar() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            String[] numAttr = params.get("numAttr").toString().split(",");
            float carType = Float.parseFloat(params.get("carType").toString());
            k = 0;
            int n = getCarNum(numAttr, carType);
            jsonResult = new JsonResult(true, "计算车辆成功", n);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "计算车辆失败");
        }
        return jsonResult;
    }

    public int getCarNum(String[] numAttr, float carType) {
        k++;
        float[] floatNum = StringToFloat(numAttr);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < numAttr.length; i++) {
            list.add(numAttr[i]);
        }
        int[] count = BestLoading.getIndex(floatNum, carType);
        while (count.length > 0) {
            list.remove(count);
        }
        if (list == null || list.isEmpty()) {
            return k;
        } else {
            String[] newStr = list.toArray(new String[1]);
            return getCarNum(newStr, carType);
        }

    }

    public float[] StringToFloat(String[] str) {
        float[] num = new float[str.length];
        for (int i = 0; i < num.length; i++) {
            num[i] = Float.parseFloat(str[i]);
        }
        return num;
    }
}
