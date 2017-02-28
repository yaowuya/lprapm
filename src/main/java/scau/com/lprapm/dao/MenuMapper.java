package scau.com.lprapm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.Menu;
import scau.com.lprapm.entity.MenuExample;

@Repository
public interface MenuMapper {
    int countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> searchMenu(Map<String, Object> params);

    List<Map<String,Object>> searchMenus(Map<String, Object> params);

    int menuInsert(Menu menu);

    List<Map<String,Object>> searchLeftMenu(Map<String, Object> params);

    @Select("<script>" +
            "select * from menu " +
            "where 1=1" +
            "<if test=\"menuIsParent !=null and menuIsParent!='' \">" +
            "and menu_is_parent=#{menuIsParent}" +
            "</if>" +
            "</script>")
    List<Map<String,Object>> selectByParentid(Map<String, Object> params);
}