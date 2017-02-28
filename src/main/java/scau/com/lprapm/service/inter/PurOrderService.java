package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/2/6.
 */
public interface PurOrderService {
    int insertPO(Orders orders, Goods goods, Receipt receipt, OrderExam orderExam, PurPrice purPrice, LogPrice logPrice);

    List<Map<String,Object>> searchPO(Map<String, Object> params);

    int updatePO(Orders orders, Goods goods, Receipt receipt);

    int deletePO(Orders orders);

    List<Map<String,Object>> searchPOP(Map<String, Object> params);

    int askPOP(Map<String, Object> params);

    int revokePOP(Map<String, Object> params);
}
