package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.Department;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/1/10.
 */
public interface DepartmentService {
    List<Map<String,Object>> searchDept(Map<String, Object> params);

    int insertDept(Department department);

    int updateDept(Department department);

    int deleteDept(int deptId);
}
