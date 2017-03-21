package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.UserMapper;
import scau.com.lprapm.dao.UserRoleMapper;
import scau.com.lprapm.entity.User;
import scau.com.lprapm.entity.UserRole;
import scau.com.lprapm.exception.UserException;
import scau.com.lprapm.service.inter.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongrf on 2016/12/13.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    public int insertMap(Map<String, Object> params){
        try{
            return userMapper.insertMap(params);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int insertMap2(User params){
        try{
            return userMapper.insertMap2(params);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public User login(String userEmail, String userPassword) throws UserException {
        User user=userMapper.loginQuery(userEmail);
        if(user==null){
            throw new UserException("该用户不存在");
        }
        if(!user.getUserPassword().equals(userPassword)){
            throw new UserException("用户名或密码错误");
        }
        return user;
    }

    @Override
    public List<Map<String, Object>> searchUser(Map<String, Object> params) {
        try {
            List<Map<String,Object>> uList=userMapper.searchUser(params);
            return uList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertUser(User user, UserRole userRole) {
        try{
            int uinsert= userMapper.insertUser(user);
            userRole.setUserId(user.getUserId());
            int urinsert=userRoleMapper.insert(userRole);
            return uinsert;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateUser(User user) {
        try {
           int update=userMapper.updateByPrimaryKey(user);
           return update;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteUser(int userId, int urId) {
        try {
            int delete=userMapper.deleteByPrimaryKey(userId);
            int deleteur=userRoleMapper.deleteByPrimaryKey(userId);
            return delete;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public List<Map<String, Object>> searchUserByName(Map<String, Object> params) {
        return userMapper.searchUserByName(params);
    }

    @Override
    public List<Map<String, Object>> searchMyUser(Map<String, Object> params) {
        return userMapper.searchMyUser(params);
    }
}
