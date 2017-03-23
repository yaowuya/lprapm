package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.PositionTracking;

import java.util.List;
import java.util.Map;

@Repository
public interface PositionTrackingMapper {
    int deleteByPrimaryKey(Integer positionId);

    int insert(PositionTracking record);

    int insertSelective(PositionTracking record);

    PositionTracking selectByPrimaryKey(Integer positionId);

    int updateByPrimaryKeySelective(PositionTracking record);

    int updateByPrimaryKey(PositionTracking record);

    @Insert("insert into position_tracking (position_id, carplan_id, " +
            " order_id,provinceid, cityid, areaid,track_status, track_time) " +
            " values (#{positionId,jdbcType=INTEGER}, #{carplanId,jdbcType=INTEGER}," +
            " #{orderId,jdbcType=INTEGER}, #{provinceid,jdbcType=VARCHAR}," +
            " #{cityid,jdbcType=VARCHAR}, #{areaid,jdbcType=VARCHAR}," +
            " #{trackStatus,jdbcType=VARCHAR},now())")
    void insertData(Map<String, Object> map);


    @Select("<script>" +
            " select p.province,c.city,a.area,t.* \n" +
            "  from ( \n" +
            "  select position_id positionId, carplan_id carplanId, " +
            "  pt.order_id orderId,pt.provinceid,pt.cityid, pt.areaid," +
            "  r.repo_address repoAddress,track_status trackStatus, " +
            "  track_time trackTime,o.user_name userName,g.goods_name goodsName " +
            "  from position_tracking pt,repertory r,orders o,goods g " +
            "  where pt.order_id=o.order_id and pt.provinceid=r.provinceid " +
            "  and pt.cityid=r.cityid and pt.areaid=r.areaid and o.goods_id=g.goods_id " +
            " <if test=\"createTime != null and createTime != '' \">" +
            "    and <![CDATA[track_time >= #{createTime}]]>" +
            " </if>" +
            " <if test=\"endTime != null and endTime != '' \">" +
            "    and <![CDATA[track_time <= #{endTime}]]>" +
            " </if>" +
            " <if test=\"goodsName != null and goodsName != '' \">" +
            "    and goods_name like concat('%',#{goodsName},'%')" +
            " </if>" +
            " <if test=\"userName != null and userName != '' \">" +
            "    and o.user_name = #{userName} " +
            " </if>" +
            " <if test=\"userId != null and userId != '' \">" +
            "    and o.user_id = #{userId} " +
            " </if>" +
            " <if test=\"orderId != null and orderId != '' \">" +
            "    and pt.order_id = #{orderId} " +
            " </if>" +
            "  ) t,provinces p,cities c,areas a\n" +
            "  where t.provinceid=p.provinceid and t.cityid=c.cityid and t.areaid=a.areaid " +
            "</script>")
    List<Map<String, Object>> queryTrack(Map<String, Object> params);
}