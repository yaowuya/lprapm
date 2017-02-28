package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.RoleMenu;
import scau.com.lprapm.entity.UserRole;
import scau.com.lprapm.entity.roleAction;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/1/23.
 */
public interface RoleSetService {
    List<Map<String,Object>> searchRoleUser(Map<String, Object> params);

    int insertRoleUser(UserRole userRole);

    int deleteRoleUser(int urId);

    UserRole queryRoleUser(UserRole userRole);

    /**
     * @param params
     * @return
     */
    int editRoleUser(Map<String, Object> params);

    List<Map<String,Object>> searchRoleAction(Map<String, Object> params);

    roleAction queryRoleAction(roleAction roleaction);

    int insertRoleAction(roleAction roleaction);

    int deleteRoleAction(int raId);

    int editRoleAction(Map<String, Object> params);

    List<Map<String,Object>> searchRoleMenu(Map<String, Object> params);

    RoleMenu queryRoleMenu(RoleMenu roleMenu);

    int insertRoleMenu(RoleMenu roleMenu);

    int editRoleMenu(Map<String, Object> params);

    int deleteRoleMenu(int rmId);
}
