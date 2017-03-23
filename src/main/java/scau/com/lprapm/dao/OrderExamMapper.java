package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.OrderExam;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderExamMapper {
    int deleteByPrimaryKey(Integer oeId);

    int insert(OrderExam record);

    int insertSelective(OrderExam record);

    OrderExam selectByPrimaryKey(Integer oeId);

    int updateByPrimaryKeySelective(OrderExam record);

    int updateByPrimaryKey(OrderExam record);

    int insertOrderExam(OrderExam orderExam);

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
            " <if test=\"isAskLog != null and isAskLog != '' \">" +
            "    and is_ask_log = #{isAskLog} " +
            " </if>" +
            " <if test=\"isSure != null and isSure != '' \">" +
            "    and is_sure= #{isSure} " +
            " </if>" +
            " <if test=\"logState != null and logState != '' \">" +
            "    and log_state = #{logState}" +
            " </if>" +
            " <if test=\"oeState != null and oeState != '' \">" +
            "    and oe_state = #{oeState}" +
            " </if>" +
            "</script>")
    List<Map<String, Object>> searchExamLog(Map<String, Object> params);
}