package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.service.inter.AddressService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Controller
@RequestMapping("address")
public class AddressController extends BaseController {
    @Autowired
    AddressService addressService;

    @ResponseBody
    @RequestMapping("province")
    public JsonResult province() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = addressService.province(params);
            jsonResult = new JsonResult(true, "查询省份成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询省份失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("city")
    public JsonResult city() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = addressService.city(params);
            jsonResult = new JsonResult(true, "查询城市成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询城市失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("area")
    public JsonResult area() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = addressService.area(params);
            jsonResult = new JsonResult(true, "查询县级市成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询县级市失败");
        }
        return jsonResult;
    }
}
