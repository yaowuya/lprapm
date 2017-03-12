package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.LogPrice;
import scau.com.lprapm.entity.OrderExam;
import scau.com.lprapm.service.inter.LogisticService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/12.
 */
@Controller
@RequestMapping("/logistic")
public class LogisticController extends BaseController {
    @Autowired
    LogisticService logisticService;

    /**
     * 格式化日期
     *
     * @param binder
     */
    @Override
    public void InitBinder(WebDataBinder binder) {
        super.InitBinder(binder);
    }

    /**
     * 物流审核 查询功能
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("searchExamLog")
    public JsonResult searchExamLog() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> list = logisticService.searchExamLog(params);
            jsonResult = new JsonResult(true, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }

    /**
     * 物流审核 审核功能
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("checkExamLog")
    public JsonResult checkExamLog(OrderExam orderExam) {
        JsonResult jsonResult = null;
        try {
            logisticService.checkExamLog(orderExam);
            jsonResult = new JsonResult(true, "审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "审核失败");
        }
        return jsonResult;
    }

    /**
     * 物流审核 撤销审核
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revokeExamLog")
    public JsonResult revokeExamLog() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            logisticService.revokeExamLog(params);
            jsonResult = new JsonResult(true, "撤销审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "撤销审核失败");
        }
        return jsonResult;
    }

    /**
     * 物流计费管理 物流询价回复 回复功能
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("checkReplyLog")
    public JsonResult checkReplyLog(LogPrice logPrice) {
        JsonResult jsonResult = null;
        try {
            logisticService.checkReplyLog(logPrice);
            jsonResult = new JsonResult(true, "回复成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "回复失败");
        }
        return jsonResult;
    }

    /**
     * 物流计费管理 物流询价回复 撤销回复功能
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revokeReplyLog")
    public JsonResult revokeReplyLog() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            logisticService.revokeReplyLog(params);
            jsonResult = new JsonResult(true, "撤销成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "撤销失败");
        }
        return jsonResult;
    }
}
