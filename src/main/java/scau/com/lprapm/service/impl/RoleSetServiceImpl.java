package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.RoleMenuMapper;
import scau.com.lprapm.dao.RoleSetMapper;
import scau.com.lprapm.dao.UserRoleMapper;
import scau.com.lprapm.dao.roleActionMapper;
import scau.com.lprapm.entity.RoleMenu;
import scau.com.lprapm.entity.UserRole;
import scau.com.lprapm.entity.roleAction;
import scau.com.lprapm.service.inter.RoleSetService;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/1/23.
 */
@Service
public class RoleSetServiceImpl implements RoleSetService {
    @Autowired
    RoleSetMapper roleSetMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    roleActionMapper roleactionMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public List<Map<String, Object>> searchRoleUser(Map<String, Object> params) {
        return roleSetMapper.searchRoleUser(params);
    }

    @Override
    public int insertRoleUser(UserRole userRole) {
        return userRoleMapper.insert(userRole);
    }

    @Override
    public int editRoleUser(Map<String, Object> params) {
        return roleSetMapper.editRoleUser(params);
    }

    @Override
    public List<Map<String, Object>> searchRoleAction(Map<String, Object> params) {
        return roleSetMapper.searchRoleAction(params);
    }

    @Override
    public roleAction queryRoleAction(roleAction roleaction) {
        return roleSetMapper.queryRoleAction(roleaction);
    }

    @Override
    public int insertRoleAction(roleAction roleaction) {
        return roleactionMapper.insert(roleaction);
    }


    @Override
    public int deleteRoleAction(int raId) {
        return roleactionMapper.deleteByPrimaryKey(raId);
    }

    @Override
    public int editRoleAction(Map<String, Object> params) {
        return roleSetMapper.editRoleAction(params);
    }

    @Override
    public List<Map<String, Object>> searchRoleMenu(Map<String, Object> params) {
        return roleSetMapper.searchRoleMenu(params);
    }

    @Override
    public RoleMenu queryRoleMenu(RoleMenu roleMenu) {
        return roleSetMapper.queryRoleMenu(roleMenu);
    }

    @Override
    public int insertRoleMenu(RoleMenu roleMenu) {
        return roleMenuMapper.insert(roleMenu);
    }

    @Override
    public int editRoleMenu(Map<String, Object> params) {
        return roleSetMapper.editRoleMenu(params);
    }

    @Override
    public int deleteRoleMenu(int rmId) {
        return roleMenuMapper.deleteByPrimaryKey(rmId);
    }

    @Override
    public int deleteRoleUser(int urId) {
        return userRoleMapper.deleteByPrimaryKey(urId);
    }

    @Override
    public UserRole queryRoleUser(UserRole userRole) {
        return roleSetMapper.queryRoleUser(userRole);
    }



}
