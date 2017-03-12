package scau.com.lprapm.dao;

import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.LogPrice;

@Repository
public interface LogPriceMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(LogPrice record);

    int insertSelective(LogPrice record);

    LogPrice selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(LogPrice record);

    int updateByPrimaryKey(LogPrice record);

    int insertLogPrice(LogPrice logPrice);
}