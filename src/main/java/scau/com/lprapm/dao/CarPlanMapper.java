package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.CarPlan;

import java.util.List;
import java.util.Map;

@Repository
public interface CarPlanMapper {
    int deleteByPrimaryKey(Integer carplanId);

    int insert(CarPlan record);

    int insertSelective(CarPlan record);

    CarPlan selectByPrimaryKey(Integer carplanId);

    int updateByPrimaryKeySelective(CarPlan record);

    int updateByPrimaryKey(CarPlan record);

    @Select("<script>" +
            "select order_id orderId, o.goods_id goodsId, " +
            " o.receipt_id receiptId, user_id userId, o.pur_id purId, o.log_id logId,o.oe_id oeId," +
            " user_name userName, is_pur isPur,is_sure isSure," +
            " is_ask_pur isAskPur,is_ask_log isAskLog,create_time createTime, end_time endTime, " +
            " o.provinceid, o.cityid, o.areaid,order_address orderAddress, goods_name goodsName, " +
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
            " from orders o,goods g,receipt r,pur_price pp,order_exam oe," +
            " log_price lp,provinces p,cities c,areas a " +
            " where o.goods_id=g.goods_id and " +
            " o.receipt_id=r.receipt_id and o.pur_id=pp.pur_id and o.oe_id=oe.oe_id " +
            " and o.log_id=lp.log_id and o.provinceid=p.provinceid and " +
            " o.cityid=c.cityid and o.areaid=a.areaid " +
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
            " <if test=\"logState != null and logState != '' \">" +
            "    and log_state = #{logState} " +
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
            " <if test=\"provinceid != null and provinceid != '' \">" +
            "    and receipt_provinceid = #{provinceid} " +
            " </if>" +
            " <if test=\"cityid != null and cityid != '' \">" +
            "    and receipt_cityid = #{cityid} " +
            " </if>" +
            " <if test=\"areaid != null and areaid != '' \">" +
            "    and receipt_areaid = #{areaid} " +
            " </if>" +
            "</script>")
    List<Map<String, Object>> searchCarS(Map<String, Object> params);
}