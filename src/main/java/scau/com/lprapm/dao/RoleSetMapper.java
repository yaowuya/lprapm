package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.RoleMenu;
import scau.com.lprapm.entity.UserRole;
import scau.com.lprapm.entity.roleAction;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/1/23.
 */
@Repository
public interface RoleSetMapper {
    @Select("<script>" +
            "select ur.ur_id Id, ur.user_id userId, ur.role_id roleId," +
            " u.user_true_name userName,u.user_email userEmail,r.role_name roleName" +
            " from user u natural join user_role ur natural join role r" +
            " where 1=1 " +
            " <if test=\"userEmail!=null and userEmail!='' \">" +
            "   and u.user_email like concat('%',#{userEmail},'%')" +
            " </if>" +
            " <if test=\"roleName!=null and roleName!=''\">" +
            "   and r.role_name like concat('%',#{roleName},'%')" +
            " </if>" +
            "</script>")
    List<Map<String,Object>> searchRoleUser(Map<String, Object> params);

    @Select("select * from user_role where user_id=#{userId} and role_id=#{roleId}")
    UserRole queryRoleUser(UserRole userRole);

    @Update("update user_role\n" +
            "    set user_id = #{userId,jdbcType=INTEGER},\n" +
            "      role_id = #{roleId,jdbcType=INTEGER}\n" +
            "    where ur_id = #{Id,jdbcType=INTEGER}")
    int editRoleUser(Map<String, Object> params);
    @Select("<script>" +
            "select r.role_name roleName,ra.ra_id Id,ra.role_id roleId," +
            " ra.action_id actionId,a.action_name actionName" +
            " from role r natural join role_action ra natural join action a" +
            " where 1=1" +
            " <if test=\"actionName!=null and actionName!=''\">" +
            "   and a.action_name like concat('%',#{actionName},'%')" +
            " </if>" +
            " <if test=\"roleName!=null and roleName!=''\">" +
            "   and r.role_name like concat('%',#{roleName},'%')" +
            " </if>" +
            "</script>")
    List<Map<String,Object>> searchRoleAction(Map<String, Object> params);

    @Select("select * from role_action where role_id=#{roleId} and action_id=#{actionId}")
    roleAction queryRoleAction(roleAction roleaction);

    @Update("update role_action\n" +
            "    set role_id = #{roleId,jdbcType=INTEGER},\n" +
            "      action_id = #{actionId,jdbcType=INTEGER}\n" +
            "    where ra_id = #{Id,jdbcType=INTEGER}")
    int editRoleAction(Map<String, Object> params);
    @Select("<script>" +
            "select r.role_name roleName,rm.rm_id Id,rm.role_id roleId," +
            " rm.menu_id menuId,m.menu_name menuName" +
            " from role r natural join role_menu rm natural join menu m" +
            " where 1=1" +
            " <if test=\"menuName!=null and menuName!=''\">" +
            "   and m.menu_name like concat('%',#{menuName},'%')" +
            " </if>" +
            " <if test=\"roleName!=null and roleName!=''\">" +
            "   and r.role_name like concat('%',#{roleName},'%')" +
            " </if>" +
            "</script>")
    List<Map<String,Object>> searchRoleMenu(Map<String, Object> params);

    @Select("select * from role_menu where role_id=#{roleId} and menu_id=#{menuId}")
    RoleMenu queryRoleMenu(RoleMenu roleMenu);
    @Update("update role_menu\n" +
            "    set role_id = #{roleId,jdbcType=INTEGER},\n" +
            "      menu_id = #{menuId,jdbcType=INTEGER}\n" +
            "    where rm_id = #{Id,jdbcType=INTEGER}")
    int editRoleMenu(Map<String, Object> params);
}
