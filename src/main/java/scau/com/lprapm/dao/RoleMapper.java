package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.Role;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Map<String,Object>> searchRoles(Map<String, Object> params);

    int insertRole(Role role);

    int updateRole(Role role);

    @Select("<script>" +
            "select r.role_id roleId, r.role_name roleName, r.role_desc roleDesc,\n" +
            "    ra.ra_id raId,a.action_id actionId,a.action_name actionName\n" +
            "    from role r NATURAL JOIN role_action ra NATURAL JOIN action a\n" +
            "    WHERE 1=1\n" +
            "    <if test=\"roleName != null and roleName != '' \">\n" +
            "      and role_name LIKE CONCAT('%',#{roleName},'%')\n" +
            "    </if>" +
            "</script>")
    List<Map<String,Object>> searchRole(Map<String, Object> params);
}