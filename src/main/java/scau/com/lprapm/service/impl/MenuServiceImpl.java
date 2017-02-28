package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.common.ProjectEntity;
import scau.com.lprapm.dao.MenuMapper;
import scau.com.lprapm.dao.RoleMenuMapper;
import scau.com.lprapm.entity.Menu;
import scau.com.lprapm.entity.MenuExample;
import scau.com.lprapm.entity.RoleMenu;
import scau.com.lprapm.service.inter.MenuService;

import java.util.*;

/**
 * Created by 钟锐锋 on 2017/1/3.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public ProjectEntity searchMenu(Map<String, Object> params) {
        try {
            ProjectEntity projectEntity = new ProjectEntity();
            List<Menu> mList = menuMapper.searchMenu(params);
            projectEntity.setList(mList);
            return projectEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> searchMenus(Map<String, Object> params) {
        try {
            List<Map<String, Object>> mList = menuMapper.searchMenus(params);
            return mList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Map<String, Object>> searchLeftMenu(Map<String, Object> params) {
        try {
            List<Map<String, Object>> mList = menuMapper.searchLeftMenu(params);
            return mList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Map<String, Object>> selectByParentid(Map<String, Object> params) {
        try {
            List<Map<String, Object>> mList = menuMapper.selectByParentid(params);
            return mList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Menu> test1(Map<String, Object> params) {
        try {
            MenuExample menuExample = new MenuExample();
            MenuExample.Criteria criteria = menuExample.createCriteria();
            criteria.andMenuNameLike("%" + params.get("menuName").toString() + "%");
            menuExample.setOrderByClause("menu_name asc");
            List<Menu> rList = menuMapper.selectByExample(menuExample);
            return rList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int menuInsert(Menu menu, RoleMenu roleMenu) {
        try {
            int insert = menuMapper.menuInsert(menu);
            int menuId=menu.getMenuId();
            roleMenu.setMenuId(menuId);
            int inserrm=roleMenuMapper.insert(roleMenu);
            return insert;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int menuDeleteById(int menuId, int rmId) {
        try{
            int delete= menuMapper.deleteByPrimaryKey(menuId);
            int deleterm=roleMenuMapper.deleteByPrimaryKey(rmId);
            return delete;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int editMenu(Menu menu) {
        return menuMapper.updateByPrimaryKey(menu);
    }

}
