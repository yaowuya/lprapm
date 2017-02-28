package scau.com.lprapm.dao;

import scau.com.lprapm.entity.OrderExam;

public interface OrderExamMapper {
    int deleteByPrimaryKey(Integer oeId);

    int insert(OrderExam record);

    int insertSelective(OrderExam record);

    OrderExam selectByPrimaryKey(Integer oeId);

    int updateByPrimaryKeySelective(OrderExam record);

    int updateByPrimaryKey(OrderExam record);

    int insertOrderExam(OrderExam orderExam);
}