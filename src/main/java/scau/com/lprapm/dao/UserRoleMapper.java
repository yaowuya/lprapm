package scau.com.lprapm.dao;

import scau.com.lprapm.entity.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer urId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer urId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    int updateByRoleId(int roleId);
}