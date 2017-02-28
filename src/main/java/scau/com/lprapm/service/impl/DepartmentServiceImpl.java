package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.DepartmentMapper;
import scau.com.lprapm.entity.Department;
import scau.com.lprapm.service.inter.DepartmentService;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/1/10.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public List<Map<String, Object>> searchDept(Map<String, Object> params) {
        try {
            List<Map<String, Object>> dList=departmentMapper.searchDeptByName(params);
            return dList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertDept(Department department) {
        try {
            return departmentMapper.insert(department);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateDept(Department department) {
        try {
            return departmentMapper.updateByPrimaryKey(department);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteDept(int deptId) {
        try {
            return departmentMapper.deleteByPrimaryKey(deptId);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
