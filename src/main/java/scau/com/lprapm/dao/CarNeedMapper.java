package scau.com.lprapm.dao;

import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.CarNeed;
import scau.com.lprapm.entity.CarPlan;

@Repository
public interface CarNeedMapper {
    int deleteByPrimaryKey(Integer carnId);

    int insert(CarNeed record);

    int insertSelective(CarNeed record);

    CarNeed selectByPrimaryKey(Integer carnId);

    int updateByPrimaryKeySelective(CarNeed record);

    int updateByPrimaryKey(CarNeed record);

    void insertCarNeed(CarNeed carNeed);
}