package scau.com.lprapm.dao;

import scau.com.lprapm.entity.CarPlan;

public interface CarPlanMapper {
    int deleteByPrimaryKey(Integer carplanId);

    int insert(CarPlan record);

    int insertSelective(CarPlan record);

    CarPlan selectByPrimaryKey(Integer carplanId);

    int updateByPrimaryKeySelective(CarPlan record);

    int updateByPrimaryKey(CarPlan record);
}