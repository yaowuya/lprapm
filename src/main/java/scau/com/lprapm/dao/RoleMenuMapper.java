package scau.com.lprapm.dao;

import scau.com.lprapm.entity.RoleMenu;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(Integer rmId);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(Integer rmId);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);

    int updateByRoleId(int roleId);
}