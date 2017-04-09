package scau.com.lprapm.controller;

import com.github.tsohr.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.Car;
import scau.com.lprapm.entity.CarNeed;
import scau.com.lprapm.entity.CarType;
import scau.com.lprapm.service.inter.VehicleService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/13.
 */
@Controller
@RequestMapping("/vehicle")
public class VehicleController extends BaseController {
    @Autowired
    VehicleService vehicleService;

    /**
     * 格式化日期
     *
     * @param binder
     */
    @Override
    public void InitBinder(WebDataBinder binder) {
        super.InitBinder(binder);
    }

    @ResponseBody
    @RequestMapping("searchUser")
    public JsonResult searchUser() {
        JsonResult jsonResult = null;
        try {
            List<Map<String, Object>> user = vehicleService.searchUser();
            jsonResult = new JsonResult(true, "查询成功", user);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("searchVehicle")
    public JsonResult searchVehicle() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = vehicleService.searchVehicle(params);
            jsonResult = new JsonResult(true, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("insertVehicle")
    public JsonResult insertVehicle(Car car) {
        JsonResult jsonResult = null;
        try {
            vehicleService.insertVehicle(car);
            jsonResult = new JsonResult(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "添加失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("updateVehicle")
    public JsonResult updateVehicle(Car car) {
        JsonResult jsonResult = null;
        try {
            vehicleService.updateVehicle(car);
            jsonResult = new JsonResult(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "修改失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("deleteVehicle")
    public JsonResult deleteVehicle() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            int carId = Integer.parseInt(params.get("carId").toString());
            vehicleService.deleteVehicle(carId);
            jsonResult = new JsonResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "删除失败");
        }
        return jsonResult;
    }

    /**
     * -------------------车辆类型管理-----------------------
     **/
    @ResponseBody
    @RequestMapping("searchCarType")
    public JsonResult searchCarType() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = vehicleService.searchCarType(params);
            jsonResult = new JsonResult(true, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("insertCarType")
    public JsonResult insertCarType(CarType carType) {
        JsonResult jsonResult = null;
        try {
            vehicleService.insertCarType(carType);
            jsonResult = new JsonResult(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "添加失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("updateCarType")
    public JsonResult updateCarType(CarType carType) {
        JsonResult jsonResult = null;
        try {
            vehicleService.updateCarType(carType);
            jsonResult = new JsonResult(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "修改失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("deleteCarType")
    public JsonResult deleteCarType() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            int ctId = Integer.parseInt(params.get("ctId").toString());
            vehicleService.deleteCarType(ctId);
            jsonResult = new JsonResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "删除失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("searchVDemand")
    public JsonResult searchVDemand() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = vehicleService.searchVDemand(params);
            jsonResult = new JsonResult(true, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("searchCarNeed")
    public JsonResult searchCarNeed() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = vehicleService.searchCarNeed(params);
            jsonResult = new JsonResult(true, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("insertVDemand")
    public JsonResult insertVDemand(CarNeed carNeed) {
        JsonResult jsonResult = null;
        try {
            vehicleService.insertVDemand(carNeed);
            jsonResult = new JsonResult(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "添加失败");
        }
        return jsonResult;
    }

}
