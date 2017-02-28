package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.Action;

import java.util.List;
import java.util.Map;

@Repository
public interface ActionMapper {
    int deleteByPrimaryKey(Integer actionId);

    int insert(Action record);

    int insertSelective(Action record);

    Action selectByPrimaryKey(Integer actionId);

    int updateByPrimaryKeySelective(Action record);

    int updateByPrimaryKey(Action record);

    @Select("<script>" +
            "select a.action_id actionId, a.action_name actionName, a.action_desc actionDesc\n" +
            "    from action a\n" +
            "    WHERE 1=1\n" +
            "    <if test=\"actionName != null and actionName != '' \">\n" +
            "      and action_name LIKE CONCAT('%',#{actionName},'%')\n" +
            "    </if>" +
            "</script>")
    List<Map<String,Object>> searchAction(Map<String, Object> params);
}