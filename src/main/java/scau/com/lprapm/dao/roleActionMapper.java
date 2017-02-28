package scau.com.lprapm.dao;

import scau.com.lprapm.entity.roleAction;

public interface roleActionMapper {
    int deleteByPrimaryKey(Integer raId);

    int insert(roleAction record);

    int insertSelective(roleAction record);

    roleAction selectByPrimaryKey(Integer raId);

    int updateByPrimaryKeySelective(roleAction record);

    int updateByPrimaryKey(roleAction record);

    int updateByActionId(int actionId);
}