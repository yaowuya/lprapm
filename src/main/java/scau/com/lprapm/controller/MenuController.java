package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.Constant;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.common.ProjectEntity;
import scau.com.lprapm.entity.Menu;
import scau.com.lprapm.entity.RoleMenu;
import scau.com.lprapm.entity.User;
import scau.com.lprapm.service.inter.MenuService;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.util.JAXBSource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/1/3.
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    MenuService menuService;

    @ResponseBody
    @RequestMapping("searchMenuTest")
    public JsonResult searchMenu(){
        /*用projectEntity查询测试*/
        JsonResult jsonResult=null;
        ProjectEntity projectEntity;
        try{
            Map<String,Object> params=super.getParamMap();
            projectEntity=menuService.searchMenu(params);
            jsonResult=new JsonResult(true,"查询成功",projectEntity);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("searchLeftMenu")
    public JsonResult searchLeftMenu(){
        /*系统根据用户角色权限加载左边菜单栏查询*/
        JsonResult jsonResult=null;
        try{
            Map<String,Object> params=new LinkedHashMap<>();
            User currentUser=(User)request.getSession().getAttribute(Constant.CURRENR_USER);
            String userEmail=currentUser.getUserEmail();
            String userPassword=currentUser.getUserPassword();
            params.put("userEmail",userEmail);
            params.put("userPassword",userPassword);
            List<Map<String,Object>> mList=menuService.searchLeftMenu(params);
            request.getSession().setAttribute("menuList",mList);
            jsonResult=new JsonResult(true,"查询成功",mList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("searchMenus")
    public JsonResult searchMenus(){
        /*菜单管理----查询功能*/
        JsonResult jsonResult=null;
        try{
            Map<String,Object> params=super.getParamMap();
            List<Map<String,Object>> mList=menuService.searchMenus(params);
            jsonResult=new JsonResult(true,"查询成功",mList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("selectByParentid")
    public JsonResult selectByParentid(){
        /*根据parentid查询，用来做下来框查询*/
        JsonResult jsonResult=null;
        try{
            Map<String,Object> params=new LinkedHashMap<>();
            params.put("menuIsParent","true");
            List<Map<String,Object>> mList=menuService.selectByParentid(params);
            jsonResult=new JsonResult(true,"查询成功",mList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("menuInsert")
    public JsonResult menuInsert(Menu menu){
        /*菜单添加功能*/
        JsonResult jsonResult=null;
        try {
            RoleMenu roleMenu=new RoleMenu();
            roleMenu.setRoleId(2);
            int insert=menuService.menuInsert(menu,roleMenu);
            jsonResult=new JsonResult(true,"插入成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("menuDeleteById")
    public JsonResult menuDeleteById(){
        /*菜单删除功能*/
        JsonResult jsonResult=null;
        try{
            Map<String,Object> params=super.getParamMap();
            int menuId=Integer.parseInt(params.get("menuId").toString());
            int rmId=Integer.parseInt(params.get("rmId").toString());
            int delete=menuService.menuDeleteById(menuId,rmId);
            jsonResult=new JsonResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("editMenu")
    public JsonResult editMenu(Menu menu){
        JsonResult jsonResult=null;
        try{
            int edit=menuService.editMenu(menu);
            jsonResult=new JsonResult(true,"编辑成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }
}
