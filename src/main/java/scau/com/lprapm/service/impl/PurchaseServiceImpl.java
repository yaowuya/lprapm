package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.PurPriceMapper;
import scau.com.lprapm.dao.purchasePriceManageMapper;
import scau.com.lprapm.entity.PurPrice;
import scau.com.lprapm.entity.purchasePriceManage;
import scau.com.lprapm.service.inter.PurchaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/10.
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    purchasePriceManageMapper purchaseMapper;
    @Autowired
    PurPriceMapper purPriceMapper;

    @Override
    public List<Map<String, Object>> searchPurchase(Map<String, Object> params) {
        return purchaseMapper.searchPurchase(params);
    }

    @Override
    public void insertPurchase(purchasePriceManage purchase) {
        purchaseMapper.insert(purchase);
    }

    @Override
    public void updatePurchase(purchasePriceManage purchase) {
        purchaseMapper.updateByPrimaryKeySelective(purchase);
    }

    @Override
    public void deletePurchase(int ppmId) {
        purchaseMapper.deleteByPrimaryKey(ppmId);
    }

    @Override
    public List<Map<String, Object>> searchExamPur(Map<String, Object> params) {
        return purchaseMapper.searchExamPur(params);
    }

    @Override
    public void checkExamPur(PurPrice purPrice) {
        purPriceMapper.updateByPrimaryKeySelective(purPrice);
    }

    @Override
    public void revokeExamPur(Map<String, Object> params) {
        PurPrice purPrice = new PurPrice();
        int purId = Integer.parseInt(params.get("purId").toString());
        purPrice.setPurId(purId);
        purPrice.setPurDept(null);
        purPrice.setPurPerson(null);
        purPrice.setPurPrice(null);
        purPrice.setPurState("否");
        purPriceMapper.updateByPrimaryKey(purPrice);
    }

    @Override
    public void checkStartPur(PurPrice purPrice) {
        purPriceMapper.updateByPrimaryKeySelective(purPrice);
    }
}
