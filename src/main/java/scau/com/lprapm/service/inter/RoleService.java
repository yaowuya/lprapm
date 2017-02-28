package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.Role;
import scau.com.lprapm.entity.roleAction;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/7.
 */
public interface RoleService {
    List<Map<String,Object>> searchRoles(Map<String, Object> params);

    int insertRole(Role role, roleAction roleaction);

    int updateRole(Role role);

    int deleteRole(int roleId, int raId);
}
