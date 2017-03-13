package scau.com.lprapm.dao;

import scau.com.lprapm.entity.CarFreeBill;

public interface CarFreeBillMapper {
    int deleteByPrimaryKey(Integer carfreeId);

    int insert(CarFreeBill record);

    int insertSelective(CarFreeBill record);

    CarFreeBill selectByPrimaryKey(Integer carfreeId);

    int updateByPrimaryKeySelective(CarFreeBill record);

    int updateByPrimaryKey(CarFreeBill record);
}