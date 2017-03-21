package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.User;
import scau.com.lprapm.entity.UserRole;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongrf on 2016/12/13.
 */
public interface UserService {

    User login(String userEmail, String userPassword);

    List<Map<String,Object>> searchUser(Map<String, Object> params);

    int insertUser(User user, UserRole userRole);

    int updateUser(User user);

    int deleteUser(int userId, int urId);

    List<Map<String,Object>> searchUserByName(Map<String, Object> params);

    List<Map<String, Object>> searchMyUser(Map<String, Object> params);
}
