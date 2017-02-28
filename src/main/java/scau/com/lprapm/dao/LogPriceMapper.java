package scau.com.lprapm.dao;

import scau.com.lprapm.entity.LogPrice;

public interface LogPriceMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(LogPrice record);

    int insertSelective(LogPrice record);

    LogPrice selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(LogPrice record);

    int updateByPrimaryKey(LogPrice record);

    int insertLogPrice(LogPrice logPrice);
}