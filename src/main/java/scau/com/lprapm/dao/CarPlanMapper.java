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
            "    and t.receiptName like concat('%',#{receiptName},'%')" +
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
    List<Map<String, Object>> searchCarS(Map<String, Object> params);
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
            " p.province receiptProvince,c.city receiptCity,a.area receiptArea," +
            " receipt_time receiptTime," +
            " pur_dept purDept, pur_person purPerson," +
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
            " <if test=\"receiptProvinceid != null and receiptProvinceid != '' \">" +
            "    and receipt_provinceid = #{receiptProvinceid} " +
            " </if>" +
            " <if test=\"receiptCityid != null and receiptCityid != '' \">" +
            "    and receipt_cityid = #{receiptCityid} " +
            " </if>" +
            " <if test=\"receiptAreaid != null and receiptAreaid != '' \">" +
            "    and receipt_areaid = #{receiptAreaid} " +
            " </if>" +
            "</script>")
    List<Map<String, Object>> searchCarSs(Map<String, Object> params);

    void insertCarPlan(CarPlan carPlan);

    @Select("<script>" +
            "select carplan_id carplanId, order_ids orderIds, " +
            "order_names orderNames, cp.carn_id carnId, " +
            "all_weight allWeight, all_number allNumber, all_volume allVolume," +
            "carplan_dept carplanDept, carplan_person carplanPerson, " +
            "carplan_desrciption carplanDesrciption, cp.provinceid, " +
            "cp.cityid, cp.areaid,p.province,c.city,a.area," +
            "carplan_time carplanTime, carn_type carnType," +
            "carn_num carnNum, carn_exam_state carnExamState, " +
            "car_exam_person carExamPerson, carn_exam_dept carnExamDept," +
            "car_ids carIds  " +
            "from car_plan cp,car_need cn,provinces p,cities c,areas a " +
            "where cp.carn_id=cn.carn_id and cp.provinceid=p.provinceid " +
            "and cp.cityid=c.cityid and cp.areaid=a.areaid " +
            " <if test=\"carnExamState != null and carnExamState != '' \">" +
            "    and carn_exam_state in ( ${carnExamState} ) " +
            " </if>" +
            " <if test=\"createTime != null and createTime != '' \">" +
            "    and <![CDATA[carplan_time >= #{createTime}]]>" +
            " </if>" +
            " <if test=\"endTime != null and endTime != '' \">" +
            "    and <![CDATA[carplan_time <= #{endTime}]]>" +
            " </if>" +
            " <if test=\"goodsName != null and goodsName != '' \">" +
            "    and order_names like concat('%',#{goodsName},'%')" +
            " </if>" +
            " <if test=\"carplanId != null and carplanId != '' \">" +
            "    and carplan_id = #{carplanId}" +
            " </if>" +
            "</script>")
    List<Map<String, Object>> searchPOS(Map<String, Object> params);
}