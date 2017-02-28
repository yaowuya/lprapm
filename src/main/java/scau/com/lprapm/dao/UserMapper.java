package scau.com.lprapm.dao;


import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.User;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertMap(Map record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User loginQuery(String userEmail);

    int insertMap2(User params);

    List<Map<String,Object>> searchUser(Map<String, Object> params);

    int insertUser(User user);

    @Insert("insert into user (user_id, user_true_name, user_password, \n" +
            "      user_email, user_phone, user_birthday, \n" +
            "      user_sex, user_company, user_dept, \n" +
            "      user_dept_phone, user_dept_desc)\n" +
            "    values (#{userId,jdbcType=INTEGER}, #{userTrueName,jdbcType=VARCHAR}, #{userPassword,jdbcType=VARCHAR}, \n" +
            "      #{userEmail,jdbcType=VARCHAR}, #{userPhone,jdbcType=VARCHAR}, #{userBirthday,jdbcType=DATE}, \n" +
            "      #{userSex,jdbcType=VARCHAR}, #{userCompany,jdbcType=VARCHAR}, #{userDept,jdbcType=VARCHAR}, \n" +
            "      #{userDeptPhone,jdbcType=VARCHAR}, #{userDeptDesc,jdbcType=VARCHAR})")
    int insertUsers(User user);

    List<Map<String,Object>> searchUserByName(Map<String, Object> params);
}