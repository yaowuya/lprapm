package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.*;
import scau.com.lprapm.entity.Role;
import scau.com.lprapm.entity.UserRole;
import scau.com.lprapm.entity.roleAction;
import scau.com.lprapm.service.inter.RoleService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/7.
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    roleActionMapper roleactionMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public List<Map<String, Object>> searchRoles(Map<String, Object> params) {
        try {
//            List<Map<String, Object>> rList=roleMapper.searchRoles(params);
            List<Map<String, Object>> rList=roleMapper.searchRole(params);
            return rList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertRole(Role role,roleAction roleaction) {
        try {
            int insert=roleMapper.insertRole(role);
            roleaction.setRoleId(role.getRoleId());
            int ra=roleactionMapper.insert(roleaction);
            return insert;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateRole(Role role) {
        try {
            int update=roleMapper.updateRole(role);
            return update;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteRole(int roleId, int raId) {
        try {
            int deleter=roleMapper.deleteByPrimaryKey(roleId);
            int deletera=roleactionMapper.deleteByPrimaryKey(raId);
            int deleterm=roleMenuMapper.updateByRoleId(roleId);
            int deleteur=userRoleMapper.updateByRoleId(roleId);
            return deleter;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

}
