package scau.com.lprapm.dao;

import scau.com.lprapm.entity.CarNeed;

public interface CarNeedMapper {
    int deleteByPrimaryKey(Integer carnId);

    int insert(CarNeed record);

    int insertSelective(CarNeed record);

    CarNeed selectByPrimaryKey(Integer carnId);

    int updateByPrimaryKeySelective(CarNeed record);

    int updateByPrimaryKey(CarNeed record);
}