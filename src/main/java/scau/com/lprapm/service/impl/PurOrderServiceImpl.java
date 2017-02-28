package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.*;
import scau.com.lprapm.entity.*;
import scau.com.lprapm.service.inter.PurOrderService;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/2/6.
 */
@Service
public class PurOrderServiceImpl implements PurOrderService{
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ReceiptMapper receiptMapper;
    @Autowired
    OrderExamMapper orderExamMapper;
    @Autowired
    PurPriceMapper purPriceMapper;
    @Autowired
    LogPriceMapper logPriceMapper;


    @Override
    public int insertPO(Orders orders, Goods goods,
                        Receipt receipt, OrderExam orderExam,
                        PurPrice purPrice, LogPrice logPrice) {
        int g=goodsMapper.insertGoods(goods);
        orders.setGoodsId(goods.getGoodsId());
        int r=receiptMapper.insertReceipt(receipt);
        orders.setReceiptId(receipt.getReceiptId());
        int oe=orderExamMapper.insertOrderExam(orderExam);
        orders.setOeId(orderExam.getOeId());
        int pp=purPriceMapper.insertPurPrice(purPrice);
        orders.setPurId(purPrice.getPurId());
        int lp=logPriceMapper.insertLogPrice(logPrice);
        orders.setLogId(logPrice.getLogId());
        orders.setIsAskPur("否");
        orders.setIsAskLog("否");
        orders.setIsSure("否");
        int o=ordersMapper.insert(orders);
        return o;
    }

    @Override
    public List<Map<String, Object>> searchPO(Map<String, Object> params) {
        return ordersMapper.searchPO(params);
    }

    @Override
    public int updatePO(Orders orders, Goods goods, Receipt receipt) {
        int o=ordersMapper.updateByPrimaryKey(orders);
        int g=goodsMapper.updateByPrimaryKey(goods);
        int r=receiptMapper.updateByPrimaryKey(receipt);
        return o;
    }

    @Override
    public int deletePO(Orders orders) {
        int g=goodsMapper.deleteByPrimaryKey(orders.getGoodsId());
        int r=receiptMapper.deleteByPrimaryKey(orders.getReceiptId());
        int oe=orderExamMapper.deleteByPrimaryKey(orders.getOeId());
        int pp=purPriceMapper.deleteByPrimaryKey(orders.getPurId());
        int lp=logPriceMapper.deleteByPrimaryKey(orders.getLogId());
        int o=ordersMapper.deleteByPrimaryKey(orders.getOrderId());
        return o;
    }

    @Override
    public List<Map<String, Object>> searchPOP(Map<String, Object> params) {
        return ordersMapper.searchPOP(params);
    }

    @Override
    public int askPOP(Map<String, Object> params) {
        return ordersMapper.askPOP(params);
    }

    @Override
    public int revokePOP(Map<String, Object> params) {
        Orders orders=new Orders();
        PurPrice purPrice=new PurPrice();
        orders.setOrderId(Integer.parseInt(params.get("orderId").toString()));
        orders.setIsAskPur("否");
        purPrice.setPurId(Integer.parseInt(params.get("purId").toString()));
        purPrice.setPurDept(null);
        purPrice.setPurPerson(null);
        purPrice.setPurPrice(null);
        purPrice.setPurState("否");
        int o=ordersMapper.updateByPrimaryKeySelective(orders);
        int p=purPriceMapper.updateByPrimaryKey(purPrice);
        return o;
    }


}
