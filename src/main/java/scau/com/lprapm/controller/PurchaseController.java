package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.PurPrice;
import scau.com.lprapm.entity.purchasePriceManage;
import scau.com.lprapm.service.inter.PurchaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/10.
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseController extends BaseController {
    @Autowired
    PurchaseService purchaseService;

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
    @RequestMapping("searchPurchase")
    public JsonResult searchPurchase() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> purList = purchaseService.searchPurchase(params);
            jsonResult = new JsonResult(true, "查询成功", purList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("insertPurchase")
    public JsonResult insertPurchase(purchasePriceManage purchase) {
        JsonResult jsonResult = null;
        try {
            purchaseService.insertPurchase(purchase);
            jsonResult = new JsonResult(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "添加失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("updatePurchase")
    public JsonResult updatePurchase(purchasePriceManage purchase) {
        JsonResult jsonResult = null;
        try {
            purchaseService.updatePurchase(purchase);
            jsonResult = new JsonResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "更新失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("deletePurchase")
    public JsonResult deletePurchase() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            int ppmId = Integer.parseInt(params.get("ppmId").toString());
            purchaseService.deletePurchase(ppmId);
            jsonResult = new JsonResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "删除失败");
        }
        return jsonResult;
    }

    /**
     * 采购审核 查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("searchExamPur")
    public JsonResult searchExamPur() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            List<Map<String, Object>> purList = purchaseService.searchExamPur(params);
            jsonResult = new JsonResult(true, "查询成功", purList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }

    /**
     * 采购审核 审核功能
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("checkExamPur")
    public JsonResult checkExamPur(PurPrice purPrice) {
        JsonResult jsonResult = null;
        try {
            purchaseService.checkExamPur(purPrice);
            jsonResult = new JsonResult(true, "审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "审核失败");
        }
        return jsonResult;
    }

    /**
     * 采购审核 撤销审核
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revokeExamPur")
    public JsonResult revokeExamPur() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            purchaseService.revokeExamPur(params);
            jsonResult = new JsonResult(true, "撤销审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "撤销审核失败");
        }
        return jsonResult;
    }

    /**
     * 采购审核 发起采购
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("checkStartPur")
    public JsonResult checkStartPur(PurPrice purPrice) {
        JsonResult jsonResult = null;
        try {
            purchaseService.checkStartPur(purPrice);
            jsonResult = new JsonResult(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "失败");
        }
        return jsonResult;
    }

}
