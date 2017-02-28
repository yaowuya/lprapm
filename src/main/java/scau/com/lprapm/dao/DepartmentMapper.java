package scau.com.lprapm.dao;

import scau.com.lprapm.entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Map<String,Object>> searchDeptByName(Map<String, Object> params);
}