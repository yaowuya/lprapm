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

    /**
     * -----------------------分割线---------------------------
     **/
    @Select("<script>" +
            "select order_id orderId, o.goods_id goodsId, " +
            " o.receipt_id receiptId, user_id userId, o.pur_id purId, o.log_id logId,o.oe_id oeId," +
            " user_name userName, is_pur isPur,is_sure isSure,is_ask_pur isAskPur, " +
            " is_ask_log isAskLog,create_time createTime, end_time endTime, " +
            " provinceid, cityid, areaid,order_address orderAddress, goods_name goodsName, " +
            " goods_number goodsNumber, goods_volume goodsVolume, " +
            " goods_perweight goodsPerweight ," +
            " receipt_name receiptName, receipt_phone receiptPhone, " +
            " receipt_address receiptAddress, receipt_state receiptState, " +
            " receipt_provinceid receiptProvinceid, receipt_cityid receiptCityid," +
            " receipt_areaid receiptAreaid, " +
            " receipt_time receiptTime, pur_dept purDept, pur_person purPerson," +
            " pur_price purPrice, pur_state purState," +
            " oe_dept oeDept,oe_person oePerson,oe_reason Reason,oe_state oeState," +
            " log_dept logDept,log_person logPerson,log_price logPrice,log_state logState " +
            " from orders o,goods g,receipt r,pur_price pp,order_exam oe,log_price lp " +
            " where o.goods_id=g.goods_id and " +
            " o.receipt_id=r.receipt_id and o.pur_id=pp.pur_id and o.oe_id=oe.oe_id " +
            " and o.log_id=lp.log_id " +
            " <if test=\"userId != null and userId != '' \">" +
            "    and user_id = #{userId} " +
            " </if>" +
            " <if test=\"userName != null and userName != '' \">" +
            "    and user_name like concat('%',#{userName},'%') " +
            " </if>" +
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
            "    and is_ask_pur = #{isAskPur} " +
            " </if>" +
            " <if test=\"isAskLog != null and isAskLog != '' \">" +
            "    and is_ask_log = #{isAskLog} " +
            " </if>" +
            " <if test=\"purState != null and purState != '' \">" +
            "    and pur_state in ( ${purState} )" +
            " </if>" +
            " <if test=\"oeState != null and oeState != '' \">" +
            "    and oe_state = #{oeState} " +
            " </if>" +
            " <if test=\"logState != null and logState != '' \">" +
            "    and log_state = #{logState} " +
            " </if>" +
            " <if test=\"isPur != null and isPur != '' \">" +
            "    and is_pur = #{isPur}" +
            " </if>"+
            " <if test=\"isSure != null and isSure != '' \">" +
            "    and is_sure = #{isSure}" +
            " </if>" +
            "</script>")
    List<Map<String, Object>> searchOrderss(Map<String, Object> params);

    @Select("<script>" +
            "select order_id orderId, o.goods_id goodsId, " +
            " user_id userId, o.pur_id purId, o.log_id logId,o.oe_id oeId," +
            " user_name userName, is_pur isPur,is_sure isSure," +
            " is_ask_pur isAskPur,is_ask_log isAskLog,create_time createTime, end_time endTime, " +
            " o.provinceid, o.cityid, o.areaid,p.province,c.city,a.area," +
            " order_address orderAddress, goods_name goodsName, " +
            " goods_number goodsNumber, goods_volume goodsVolume, " +
            " goods_perweight goodsPerweight ," +
            " pur_dept purDept, pur_person purPerson," +
            " pur_price purPrice, pur_state purState," +
            " oe_dept oeDept,oe_person oePerson,oe_reason Reason,oe_state oeState," +
            " log_dept logDept,log_person logPerson,log_price logPrice,log_state logState," +
            " t.* from" +
            " (select receipt_id receiptId,receipt_name receiptName, receipt_phone receiptPhone, " +
            " receipt_address receiptAddress, receipt_state receiptState, " +
            " receipt_provinceid receiptProvinceid, receipt_cityid receiptCityid," +
            " receipt_areaid receiptAreaid, " +
            " rp.province receiptProvince,rc.city receiptCity,ra.area receiptArea," +
            " receipt_time receiptTime  " +
            "from receipt r,provinces rp,cities rc,areas ra " +
            "where receipt_provinceid=rp.provinceid and receipt_cityid=rc.cityid and" +
            " receipt_areaid=ra.areaid) t,orders o,goods g,pur_price pp,order_exam oe," +
            " log_price lp,provinces p,cities c,areas a " +
            " where o.goods_id=g.goods_id and " +
            " o.receipt_id=t.receiptId and o.pur_id=pp.pur_id and o.oe_id=oe.oe_id " +
            " and o.log_id=lp.log_id and o.provinceid=p.provinceid and " +
            " o.cityid=c.cityid and o.areaid=a.areaid " +
            " <if test=\"userId != null and userId != '' \">" +
            "    and user_id = #{userId} " +
            " </if>" +
            " <if test=\"userName != null and userName != '' \">" +
            "    and user_name like concat('%',#{userName},'%') " +
            " </if>" +
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
            "    and r.receipt_name like concat('%',#{receiptName},'%')" +
            " </if>" +
            " <if test=\"isAskPur != null and isAskPur != '' \">" +
            "    and is_ask_pur = #{isAskPur} " +
            " </if>" +
            " <if test=\"isAskLog != null and isAskLog != '' \">" +
            "    and is_ask_log = #{isAskLog} " +
            " </if>" +
            " <if test=\"purState != null and purState != '' \">" +
            "    and pur_state in ( ${purState} )" +
            " </if>" +
            " <if test=\"oeState != null and oeState != '' \">" +
            "    and oe_state = #{oeState} " +
            " </if>" +
            " <if test=\"logState != null and logState != '' \">" +
            "    and log_state = #{logState} " +
            " </if>" +
            " <if test=\"isPur != null and isPur != '' \">" +
            "    and is_pur = #{isPur}" +
            " </if>" +
            " <if test=\"isSure != null and isSure != '' \">" +
            "    and is_sure = #{isSure}" +
            " </if>" +
            " <if test=\"province != null and province != '' \">" +
            "    and p.province = #{province} " +
            " </if>" +
            " <if test=\"city != null and city != '' \">" +
            "    and c.city = #{city} " +
            " </if>" +
            " <if test=\"area != null and area != '' \">" +
            "    and a.area = #{area} " +
            " </if>" +
            " <if test=\"receiptProvinceid != null and receiptProvinceid != '' \">" +
            "    and receiptProvinceid = #{receiptProvinceid} " +
            " </if>" +
            " <if test=\"receiptCityid != null and receiptCityid != '' \">" +
            "    and receiptCityid = #{receiptCityid} " +
            " </if>" +
            " <if test=\"receiptAreaid != null and receiptAreaid != '' \">" +
            "    and receiptAreaid = #{receiptAreaid} " +
            " </if>" +
            "</script>")
    List<Map<String, Object>> searchOrders(Map<String, Object> params);

    @Update("<script>" +
            "update orders " +
            "set " +
            " <if test=\"isAskPur != null and isAskPur != '' \">" +
            "    is_ask_pur = #{isAskPur,jdbcType=VARCHAR} " +
            " </if>" +
            " <if test=\"isAskLog != null and isAskLog != '' \">" +
            "    is_ask_log = #{isAskLog,jdbcType=VARCHAR} " +
            " </if>"+
            " <if test=\"isSure != null and isSure != '' \">" +
            "    is_sure = #{isSure,jdbcType=VARCHAR} " +
            " </if>" +
            "where order_id = #{orderId,jdbcType=INTEGER}" +
            "</script>")
    void askOrders(Map<String, Object> params);

    @Update("<script>" +
            "update pur_price " +
            "set " +
            " <if test=\"purState != null and purState != '' \">" +
            "    pur_state = #{purState,jdbcType=VARCHAR} " +
            " </if>" +
            "where pur_id = #{purId,jdbcType=INTEGER}" +
            "</script>")
    void askSP(Map<String, Object> params);

    @Update("<script>" +
            "update order_exam " +
            "set " +
            " <if test=\"oeState != null and oeState != '' \">" +
            "    oe_state = #{oeState,jdbcType=VARCHAR} " +
            " </if>" +
            "where oe_id = #{oeId,jdbcType=INTEGER}" +
            "</script>")
    void askLOP(Map<String, Object> params);

    @Update("<script>" +
            "update orders " +
            "set " +
            " <if test=\"isSure != null and isSure != '' \">" +
            "    is_sure = #{isSure,jdbcType=VARCHAR} " +
            " </if>" +
            "where order_id = #{orderId,jdbcType=INTEGER}" +
            "</script>")
    void revokeSC(Map<String, Object> params);

    @Update("<script>" +
            "update log_price " +
            "set log_state = '已出发' " +
            "where log_id in (select o.log_id from orders o where order_id = #{orderId})" +
            "</script>")
    void updateLogState(Map<String, Object> map);

    @Update("<script>" +
            "update log_price " +
            "set log_state = '准备出发' " +
            "where log_id in (select o.log_id from orders o where order_id = #{orderId})" +
            "</script>")
    void editLogState(Map<String, Object> map);

    @Select("<script>" +
            "select order_id orderId,user_name userName " +
            " from orders o " +
            " where 1=1 " +
            " <if test=\"userName != null and userName != '' \">" +
            "   and user_name = #{userName,jdbcType=VARCHAR} " +
            " </if>" +
            "</script>")
    List<Map<String, Object>> queryOrders(Map<String, Object> params);
}