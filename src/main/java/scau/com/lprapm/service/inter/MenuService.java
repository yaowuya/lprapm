package scau.com.lprapm.service.inter;

import scau.com.lprapm.common.ProjectEntity;
import scau.com.lprapm.entity.Menu;
import scau.com.lprapm.entity.RoleMenu;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/1/3.
 */
public interface MenuService {
    ProjectEntity searchMenu(Map<String, Object> params);

    List<Map<String,Object>> searchMenus(Map<String, Object> params);

    List<Map<String,Object>> selectByParentid(Map<String, Object> params);
    
    List<Menu> test1(Map<String, Object> params);

    int menuInsert(Menu menu, RoleMenu roleMenu);

    int menuDeleteById(int menuId, int rmId);

    int editMenu(Menu menu);

    List<Map<String,Object>> searchLeftMenu(Map<String, Object> params);
}
