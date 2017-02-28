package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.Constant;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.*;
import scau.com.lprapm.service.inter.PurOrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/2/6.
 */
@Controller
@RequestMapping("/purchaseOrder")
public class PurOrderController extends BaseController {
     @Autowired
     PurOrderService purOrderService;
     @Autowired
    HttpServletRequest request;

    /**
     * 添加采购订单
     * @param orders
     * @param goods
     * @param receipt
     * @param orderExam
     * @param purPrice
     * @param logPrice
     * @return
     */
     @ResponseBody
     @RequestMapping("insertPO")
     public JsonResult insertPO(Orders orders, Goods goods,Receipt receipt,
                                OrderExam orderExam, PurPrice purPrice,
                                LogPrice logPrice){
         JsonResult jsonResult=null;
         try {
             User current=(User)request.getSession().getAttribute(Constant.CURRENR_USER);
             int userId=current.getUserId();
             orders.setUserId(userId);
             String userName=current.getUserTrueName();
             orders.setUserName(userName);
             int inpo=purOrderService.insertPO(orders,goods,receipt,orderExam,purPrice,logPrice);
             jsonResult=new JsonResult(true,"添加成功",inpo);

         }catch (Exception e){
             e.printStackTrace();
             jsonResult=new JsonResult(false,"添加失败");
         }
         return jsonResult;
     }

    /**
     * 格式化日期
     * @param binder
     */
     @Override
     public void InitBinder(WebDataBinder binder) {
        super.InitBinder(binder);
     }

    /**
     * 查询采购订单
     * @return
     */
     @ResponseBody
     @RequestMapping("searchPO")
     public JsonResult searchPO(){
         JsonResult jsonResult=null;
         try {
             User current=(User)request.getSession().getAttribute(Constant.CURRENR_USER);
             int userId=current.getUserId();
             Map<String,Object> params=super.getParamMap();
             params.put("userId",userId);
             List<Map<String,Object>> poList=purOrderService.searchPO(params);
             jsonResult=new JsonResult(true,"查询成功",poList);
         }catch (Exception e){
             e.printStackTrace();
             jsonResult=new JsonResult(false,"查询失败");
         }
         return jsonResult;
     }

    /**
     * 修改采购订单
     * @param orders
     * @param goods
     * @param receipt
     * @return
     */
     @ResponseBody
     @RequestMapping("updatePO")
     public JsonResult updatePO(Orders orders, Goods goods,Receipt receipt){
         JsonResult jsonResult=null;
         try {
             int upPO=purOrderService.updatePO(orders,goods,receipt);
             jsonResult=new JsonResult(true,"修改成功",upPO);
         }catch (Exception e){
             e.printStackTrace();
             jsonResult=new JsonResult(false,"更新失败");
         }
         return jsonResult;
     }

    /**
     * 删除采购订单
     * @param orders
     * @return
     */
    @ResponseBody
    @RequestMapping("deletePO")
    public JsonResult deletePO(Orders orders){
         JsonResult jsonResult=null;
         try {
             int dePO=purOrderService.deletePO(orders);
             jsonResult=new JsonResult(true,"删除成功",dePO);
         }catch (Exception e){
             e.printStackTrace();
             jsonResult=new JsonResult(false,"删除失败");
         }
        return jsonResult;
    }

    /**
     * 采购询价管理--查询功能
     * @return
     */
    @ResponseBody
    @RequestMapping("searchPOP")
    public JsonResult searchPOP(){
        JsonResult jsonResult=null;
        try {
            User current=(User)request.getSession().getAttribute(Constant.CURRENR_USER);
            int userId=current.getUserId();
            Map<String,Object> params=super.getParamMap();
            params.put("userId",userId);
            List<Map<String,Object>> popList=purOrderService.searchPOP(params);
            jsonResult=new JsonResult(true,"查询采购询价成功",popList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"查询采购询价失败");
        }
        return jsonResult;
    }

    /**
     * 采购询价管理  发起采购询价
     * @return
     */
    @ResponseBody
    @RequestMapping("askPOP")
    public JsonResult askPOP(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            int askPOP=purOrderService.askPOP(params);
            jsonResult=new JsonResult(true,"发起采购询价成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"发起采购询价成功");
        }
        return jsonResult;
    }

    /**
     * 采购询价管理 撤销采购询价
     * @return
     */
    @ResponseBody
    @RequestMapping("revokePOP")
    public JsonResult revokePOP(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            int rPOP=purOrderService.revokePOP(params);
            jsonResult=new JsonResult(true,"撤销采购询价成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"撤销采购询价失败");
        }
        return jsonResult;
    }

}
