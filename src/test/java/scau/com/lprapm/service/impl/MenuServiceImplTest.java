package scau.com.lprapm.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import scau.com.lprapm.common.ProjectEntity;
import scau.com.lprapm.entity.Menu;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by 钟锐锋 on 2017/1/4.
 */
public class MenuServiceImplTest extends BaseTest{
    @Test
    public void selectByParentid() throws Exception {
        Map<String,Object> params=new LinkedHashMap<>();
        params.put("menuIsParent","true");
        List<Map<String,Object>> rList=menuService.selectByParentid(params);
        System.out.println(rList);
    }

    @Test
    public void test1() throws Exception {
        Map<String,Object> params=new LinkedHashMap<>();
        params.put("menuName","户");
        List<Menu> rList=menuService.test1(params);
        System.out.println(rList);
    }

    @Test
    public void searchMenus() throws Exception {
        Map<String,Object> params=new LinkedHashMap<>();
        params.put("menuName","菜单");
        List<Map<String,Object>> rList=menuService.searchMenus(params);
        System.out.println("rlist:"+rList);
    }

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    MenuServiceImpl menuService;

    @Test
    public void searchMenu() throws Exception {
        Map<String,Object> params=new LinkedHashMap<>();
        params.put("menuName","");
        ProjectEntity projectEntity=menuService.searchMenu(params);
        System.out.println(projectEntity.getList());
    }

}