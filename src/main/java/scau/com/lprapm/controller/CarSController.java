package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.Constant;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.CarNeed;
import scau.com.lprapm.entity.CarPlan;
import scau.com.lprapm.entity.User;
import scau.com.lprapm.service.inter.CarSService;
import scau.com.lprapm.service.inter.OrdersService;

import javax.servlet.http.HttpServletRequest;
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
}
