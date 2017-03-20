package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.PositionTracking;

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
}