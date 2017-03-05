package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.Constant;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.*;
import scau.com.lprapm.service.inter.OrdersService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/5.
 */
@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    HttpServletRequest request;

    /**
     * 添加采购订单
     *
     * @param orders
     * @param goods
     * @param receipt
     * @param orderExam
     * @param purPrice
     * @param logPrice
     * @return
     */
    @ResponseBody
    @RequestMapping("insertOrders")
    public JsonResult insertOrders(Orders orders, Goods goods, Receipt receipt,
                                   OrderExam orderExam, PurPrice purPrice,
                                   LogPrice logPrice) {
        JsonResult jsonResult = null;
        try {
            User current = (User) request.getSession().getAttribute(Constant.CURRENR_USER);
            int userId = current.getUserId();
            orders.setUserId(userId);
            String userName = current.getUserTrueName();
            orders.setUserName(userName);
            ordersService.insertOrders(orders, goods, receipt, orderExam, purPrice, logPrice);
            jsonResult = new JsonResult(true, "添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "添加失败");
        }
        return jsonResult;
    }

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
     * 查询采购订单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("searchOrders")
    public JsonResult searchOrders() {
        JsonResult jsonResult = null;
        try {
            User current = (User) request.getSession().getAttribute(Constant.CURRENR_USER);
            int userId = current.getUserId();
            Map<String, Object> params = super.getParamMap();
            params.put("userId", userId);
            List<Map<String, Object>> poList = ordersService.searchOrders(params);
            jsonResult = new JsonResult(true, "查询成功", poList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "查询失败");
        }
        return jsonResult;
    }

    /**
     * 修改采购订单
     *
     * @param orders
     * @param goods
     * @param receipt
     * @return
     */
    @ResponseBody
    @RequestMapping("updateOrders")
    public JsonResult updateOrders(Orders orders, Goods goods, Receipt receipt) {
        JsonResult jsonResult = null;
        try {
            ordersService.updateOrders(orders, goods, receipt);
            jsonResult = new JsonResult(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "更新失败");
        }
        return jsonResult;
    }

    /**
     * 删除采购订单
     *
     * @param orders
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteOrders")
    public JsonResult deleteOrders(Orders orders) {
        JsonResult jsonResult = null;
        try {
            ordersService.deleteOrders(orders);
            jsonResult = new JsonResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "删除失败");
        }
        return jsonResult;
    }

    /**
     * 发起询价
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("askOrders")
    public JsonResult askOrders() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            ordersService.askOrders(params);
            jsonResult = new JsonResult(true, "发起询价成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "发起询价成功");
        }
        return jsonResult;
    }

    /**
     * 采购询价管理 撤销采购询价
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revokePOP")
    public JsonResult revokePOP() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            ordersService.revokePOP(params);
            jsonResult = new JsonResult(true, "撤销采购询价成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "撤销采购询价失败");
        }
        return jsonResult;
    }

    /**
     * 物流询价管理 撤销采购询价
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("revokeLOP")
    public JsonResult revokeLOP() {
        JsonResult jsonResult = null;
        try {
            Map<String, Object> params = super.getParamMap();
            ordersService.revokeLOP(params);
            jsonResult = new JsonResult(true, "撤销物流询价成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(false, "撤销物流询价失败");
        }
        return jsonResult;
    }
}
