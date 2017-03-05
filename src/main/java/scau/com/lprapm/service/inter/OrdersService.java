package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/5.
 */
public interface OrdersService {
    void insertOrders(Orders orders, Goods goods, Receipt receipt, OrderExam orderExam, PurPrice purPrice, LogPrice logPrice);

    List<Map<String, Object>> searchOrders(Map<String, Object> params);

    void updateOrders(Orders orders, Goods goods, Receipt receipt);

    void deleteOrders(Orders orders);

    void askOrders(Map<String, Object> params);

    void revokePOP(Map<String, Object> params);

    void revokeLOP(Map<String, Object> params);
}
