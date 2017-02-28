package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.Orders;

import java.util.List;
import java.util.Map;

@Repository
public interface OrdersMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    @Select("<script>" +
            "select order_id orderId, o.goods_id goodsId, o.oe_id oeId, " +
            " o.receipt_id receiptId, user_id userId, o.pur_id purId, " +
            " o.log_id logId, user_name userName, is_pur isPur, " +
            " is_ask_pur isAskPur, is_ask_log isAskLog, is_sure isSure, " +
            " create_time createTime, end_time endTime, " +
            " order_address orderAddress, goods_name goodsName, " +
            " goods_number goodsNumber, goods_volume goodsVolume, " +
            " goods_perweight goodsPerweight , oe_state oeState, " +
            " oe_person oePerson, oe_dept oeDept, oe_reason oeReason, " +
            " receipt_name receiptName, receipt_phone receiptPhone, " +
            " receipt_address receiptAddress, receipt_state receiptState, " +
            " receipt_time receiptTime, pur_dept purDept, pur_person purPerson," +
            " pur_price purPrice, pur_state purState, log_dept logDept, " +
            " log_person logPerson, log_price logPrice, log_state logState " +
            "from orders o,goods g,order_exam oe,receipt r,pur_price pp," +
            " log_price lp " +
            "where o.goods_id=g.goods_id and o.oe_id=oe.oe_id and " +
            " o.receipt_id=r.receipt_id and o.pur_id=pp.pur_id and " +
            " o.log_id=lp.log_id and user_id=#{userId}" +
            " <if test=\"createTime != null and createTime != '' \">" +
            "    and <![CDATA[create_time >= #{createTime}]]>" +
            " </if>" +
            " <if test=\"endTime != null and endTime != '' \">" +
            "    and <![CDATA[create_time <= #{endTime}]]>" +
            " </if>" +
            " <if test=\"goodsName != null and goodsName != '' \">" +
            "    and goods_name like concat('%',#{goodsName},'%')" +
            " </if>" +
            " <if test=\"receiptName != null and receiptName != '' \">" +
            "    and receipt_name like concat('%',#{receiptName},'%')" +
            " </if>" +
            "</script>")
    List<Map<String,Object>> searchPO(Map<String, Object> params);

    @Select("<script>" +
            "select order_id orderId, o.goods_id goodsId, " +
            " o.receipt_id receiptId, user_id userId, o.pur_id purId, " +
            " user_name userName, is_pur isPur,is_ask_pur isAskPur, " +
            " create_time createTime, end_time endTime, " +
            " order_address orderAddress, goods_name goodsName, " +
            " goods_number goodsNumber, goods_volume goodsVolume, " +
            " goods_perweight goodsPerweight ," +
            " receipt_name receiptName, receipt_phone receiptPhone, " +
            " receipt_address receiptAddress, receipt_state receiptState, " +
            " receipt_time receiptTime, pur_dept purDept, pur_person purPerson," +
            " pur_price purPrice, pur_state purState "+
            " from orders o,goods g,receipt r,pur_price pp " +
            " where o.goods_id=g.goods_id and " +
            " o.receipt_id=r.receipt_id and o.pur_id=pp.pur_id " +
            " and user_id=#{userId}" +
            " <if test=\"createTime != null and createTime != '' \">" +
            "    and <![CDATA[create_time >= #{createTime}]]>" +
            " </if>" +
            " <if test=\"endTime != null and endTime != '' \">" +
            "    and <![CDATA[create_time <= #{endTime}]]>" +
            " </if>" +
            " <if test=\"goodsName != null and goodsName != '' \">" +
            "    and goods_name like concat('%',#{goodsName},'%')" +
            " </if>" +
            " <if test=\"receiptName != null and receiptName != '' \">" +
            "    and receipt_name like concat('%',#{receiptName},'%')" +
            " </if>" +
            " <if test=\"isAskPur != null and isAskPur != '' \">" +
            "    and is_ask_pur like concat('%',#{isAskPur},'%')" +
            " </if>"+
            "</script>")
    List<Map<String,Object>> searchPOP(Map<String, Object> params);

    @Update("<script>" +
            "update orders " +
            "set is_ask_pur = #{isAskPur,jdbcType=VARCHAR} " +
            "where order_id = #{orderId,jdbcType=INTEGER}" +
            "</script>")
    int askPOP(Map<String, Object> params);
}