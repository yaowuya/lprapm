package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.Repertory;
import scau.com.lprapm.service.inter.RepertoryService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/18.
 */
@Controller
@RequestMapping("/repertory")
public class RepertoryController extends BaseController {
    @Autowired
    RepertoryService repertoryService;

    @ResponseBody
    @RequestMapping("searchReper")
    public JsonResult searchReper() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = repertoryService.searchReper(params);
            jsonResult = new JsonResult(true, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("insertReper")
    public JsonResult insertReper(Repertory repertory) {
        JsonResult jsonResult = null;
        try {
            repertoryService.insertReper(repertory);
            jsonResult = new JsonResult(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "添加失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("updateReper")
    public JsonResult updateReper(Repertory repertory) {
        JsonResult jsonResult = null;
        try {
            repertoryService.updateReper(repertory);
            jsonResult = new JsonResult(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "修改失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("deleteReper")
    public JsonResult deleteReper() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            int repoId = Integer.parseInt(params.get("repoId").toString());
            repertoryService.deleteReper(repoId);
            jsonResult = new JsonResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "删除失败");
        }
        return jsonResult;
    }
}
