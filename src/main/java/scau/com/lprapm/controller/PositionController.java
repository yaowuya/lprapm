package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.service.inter.CarSService;
import scau.com.lprapm.service.inter.OrdersService;
import scau.com.lprapm.service.inter.PositionService;

import javax.xml.bind.util.JAXBSource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/20.
 */

@Controller
@RequestMapping("/position")
public class PositionController extends BaseController {
    @Autowired
    PositionService positionService;
    @Autowired
    CarSService carSService;
    @Autowired
    OrdersService ordersService;

    @ResponseBody
    @RequestMapping("queryPOS")
    public JsonResult queryPOS() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = carSService.searchPOS(params);
            jsonResult = new JsonResult(true, "查询配送成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询配送失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("insertPOS")
    public JsonResult insertPOS() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            positionService.insertPOS(params);
            jsonResult = new JsonResult(true, "登记成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "登记失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("queryOrders")
    public JsonResult queryOrders() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = ordersService.queryOrders(params);
            jsonResult = new JsonResult(true, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("queryTrack")
    public JsonResult queryTrack() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = positionService.queryTrack(params);
            jsonResult = new JsonResult(true, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }
}
