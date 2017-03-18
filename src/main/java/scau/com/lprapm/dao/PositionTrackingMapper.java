package scau.com.lprapm.dao;

import scau.com.lprapm.entity.PositionTracking;

public interface PositionTrackingMapper {
    int deleteByPrimaryKey(Integer positionId);

    int insert(PositionTracking record);

    int insertSelective(PositionTracking record);

    PositionTracking selectByPrimaryKey(Integer positionId);

    int updateByPrimaryKeySelective(PositionTracking record);

    int updateByPrimaryKey(PositionTracking record);
}