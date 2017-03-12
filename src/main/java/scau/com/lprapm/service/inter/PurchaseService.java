package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.PurPrice;
import scau.com.lprapm.entity.purchasePriceManage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface PurchaseService {
    List<Map<String, Object>> searchPurchase(Map<String, Object> params);

    void insertPurchase(purchasePriceManage purchase);

    void updatePurchase(purchasePriceManage purchase);

    void deletePurchase(int ppmId);

    List<Map<String, Object>> searchExamPur(Map<String, Object> params);

    void checkExamPur(PurPrice purPrice);

    void revokeExamPur(Map<String, Object> params);

    void checkStartPur(PurPrice purPrice);
}
