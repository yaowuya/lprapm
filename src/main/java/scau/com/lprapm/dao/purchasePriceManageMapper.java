package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.purchasePriceManage;

import java.util.List;
import java.util.Map;

@Repository
public interface purchasePriceManageMapper {
    int deleteByPrimaryKey(Integer ppmId);

    int insert(purchasePriceManage record);

    int insertSelective(purchasePriceManage record);

    purchasePriceManage selectByPrimaryKey(Integer ppmId);

    int updateByPrimaryKeySelective(purchasePriceManage record);

    int updateByPrimaryKey(purchasePriceManage record);

    @Select("<script>" +
            "select ppm_id ppmId, goods_name goodsName," +
            " goods_price goodsPrice,ppm_time ppmTime " +
            " from purchase_price_manage" +
            " where 1=1" +
            " <if test=\"createTime != null and createTime != '' \">" +
            "    and <![CDATA[ppm_time >= #{createTime}]]>" +
            " </if>" +
            " <if test=\"endTime != null and endTime != '' \">" +
            "    and <![CDATA[ppm_time <= #{endTime}]]>" +
            " </if>" +
            " <if test=\"goodsName != null and goodsName != '' \">" +
            "    and goods_name like concat('%',#{goodsName},'%')" +
            " </if>" +
            "</script>")
    List<Map<String, Object>> searchPurchase(Map<String, Object> params);

    @Select("<script>" +
            "select order_id orderId, o.goods_id goodsId, " +
            " o.receipt_id receiptId, user_id userId, o.pur_id purId, o.log_id logId,o.oe_id oeId," +
            " user_name userName, is_pur isPur,is_ask_pur isAskPur, " +
            " is_ask_log isAskLog,create_time createTime, end_time endTime, " +
            " order_address orderAddress, goods_name goodsName, " +
            " goods_number goodsNumber, goods_volume goodsVolume, " +
            " goods_perweight goodsPerweight ," +
            " pur_dept purDept, pur_person purPerson," +
            " pur_price purPrice, pur_state purState " +
            " from orders o,goods g,pur_price pp " +
            " where o.goods_id=g.goods_id and " +
            " o.pur_id=pp.pur_id  " +
            " <if test=\"createTime != null and createTime != '' \">" +
            "    and <![CDATA[create_time >= #{createTime}]]>" +
            " </if>" +
            " <if test=\"endTime != null and endTime != '' \">" +
            "    and <![CDATA[create_time <= #{endTime}]]>" +
            " </if>" +
            " <if test=\"goodsName != null and goodsName != '' \">" +
            "    and goods_name like concat('%',#{goodsName},'%')" +
            " </if>" +
            " <if test=\"isAskPur != null and isAskPur != '' \">" +
            "    and is_ask_pur = #{isAskPur} " +
            " </if>" +
            " <if test=\"purState != null and purState != '' \">" +
            "    and pur_state in ( ${purState} )" +
            " </if>" +
            "</script>")
    List<Map<String, Object>> searchExamPur(Map<String, Object> params);
}