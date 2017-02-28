package scau.com.lprapm.controller;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.RoleMenu;
import scau.com.lprapm.entity.User;
import scau.com.lprapm.entity.UserRole;
import scau.com.lprapm.entity.roleAction;
import scau.com.lprapm.service.inter.*;

import java.util.List;
import java.util.Map;


/**
 * Created by 钟锐锋 on 2017/1/23.
 */
@Controller
@RequestMapping("/roleSet")
public class RoleSetController extends BaseController{
   @Autowired
   RoleSetService roleSetService;
   @Autowired
    RoleService roleService;
   @Autowired
    UserService userService;
   @Autowired
    ActionService actionService;
   @Autowired
    MenuService menuService;
    /**
     * 查询用户角色设置信息
     * @return
     */
   @ResponseBody
   @RequestMapping("searchRoleUser")
   public JsonResult searchRoleUser(){
      JsonResult jsonResult=null;
      try {
          Map<String,Object> params=super.getParamMap();
          List<Map<String,Object>> ruList=roleSetService.searchRoleUser(params);
          jsonResult=new JsonResult(true,"查询成功",ruList);
      }catch (Exception e){
          e.printStackTrace();
          jsonResult=new JsonResult(false,"查询失败");
      }
      return jsonResult;
   }

    /**
     * 查询角色权限信息
     * @return
     */
    @ResponseBody
    @RequestMapping("searchRoleAction")
    public JsonResult searchRoleAction(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            List<Map<String,Object>> raList=roleSetService.searchRoleAction(params);
            jsonResult=new JsonResult(true,"查询成功",raList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"查询失败");
        }
        return jsonResult;
    }

    /**
     * 查询角色菜单配置信息
     * @return
     */
    @ResponseBody
    @RequestMapping("searchRoleMenu")
    public JsonResult searchRoleMenu(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            List<Map<String,Object>> raList=roleSetService.searchRoleMenu(params);
            jsonResult=new JsonResult(true,"查询成功",raList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"查询失败");
        }
        return jsonResult;
    }
    /**
     * 查询角色下来框
     * @return
     */
   @ResponseBody
   @RequestMapping("searchrole")
   public JsonResult searchRole(){
       JsonResult jsonResult=null;
       try {
           Map<String,Object> params=super.getParamMap();
           List<Map<String,Object>> rList=roleService.searchRoles(params);
           jsonResult=new JsonResult(true,"查询角色成功",rList);
       }catch (Exception e){
           e.printStackTrace();
           jsonResult=new JsonResult(false,"查询角色失败");
       }
       return jsonResult;
   }

    /**
     * 查询用户下来框
     * @return
     */
    @ResponseBody
    @RequestMapping("searchuser")
    public JsonResult searchUser(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            List<Map<String,Object>> uList=userService.searchUserByName(params);
            jsonResult=new JsonResult(true,"查询角色成功",uList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"查询用户失败");
        }
        return jsonResult;
    }

    /**
     * 查询权限下来框
     * @return
     */
    @ResponseBody
    @RequestMapping("searchaction")
    public JsonResult searchAction(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            List<Map<String,Object>> aList=actionService.searchAction(params);
            jsonResult=new JsonResult(true,"查询权限成功",aList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"查询权限失败");
        }
        return jsonResult;
    }

    /**
     * 查询菜单下拉框
     * @return
     */
    @ResponseBody
    @RequestMapping("searchmenu")
    public JsonResult searchmenu(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            List<Map<String,Object>> aList=menuService.searchMenus(params);
            jsonResult=new JsonResult(true,"查询菜单成功",aList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"查询菜单失败");
        }
        return jsonResult;
    }
    /**
     * 添加用户角色配置
     * @return
     */
    @ResponseBody
    @RequestMapping("insertRoleUser")
    public JsonResult insertRoleUser(UserRole userRole){
        JsonResult jsonResult=null;
        try {
            UserRole qur=roleSetService.queryRoleUser(userRole);
            if(qur!=null&&!qur.equals("")){
                jsonResult=new JsonResult(false,"该配置已经存在",qur);
            }else{
                int iru=roleSetService.insertRoleUser(userRole);
                jsonResult=new JsonResult(true,"添加成功",iru);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"添加失败");
        }
        return jsonResult;
    }


    /**
     * 添加角色权限配置
     * @param roleaction
     * @return
     */
    @ResponseBody
    @RequestMapping("insertRoleAction")
    public JsonResult insertRoleAction(roleAction roleaction){
        JsonResult jsonResult=null;
        try {
            roleAction qra=roleSetService.queryRoleAction(roleaction);
            if(qra!=null&&!qra.equals("")){
                jsonResult=new JsonResult(false,"该配置已经存在",qra);
            }else{
                int iru=roleSetService.insertRoleAction(roleaction);
                jsonResult=new JsonResult(true,"添加成功",iru);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"添加失败");
        }
        return jsonResult;
    }

    /**
     * 添加角色菜单信息
     * @param roleMenu
     * @return
     */
    @ResponseBody
    @RequestMapping("insertRoleMenu")
    public JsonResult insertRoleMenu(RoleMenu roleMenu){
        JsonResult jsonResult=null;
        try {
            RoleMenu qrm=roleSetService.queryRoleMenu(roleMenu);
            if(qrm!=null&&!qrm.equals("")){
                jsonResult=new JsonResult(false,"该配置已经存在",qrm);
            }else{
                int iru=roleSetService.insertRoleMenu(roleMenu);
                jsonResult=new JsonResult(true,"添加成功",iru);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"添加失败");
        }
        return jsonResult;
    }
    /**
     * 修改用户角色配置
     * @return
     */
    @ResponseBody
    @RequestMapping("editRoleUser")
    public JsonResult editRoleUser(UserRole userRole){
        JsonResult jsonResult=null;
        try {
            UserRole qur=roleSetService.queryRoleUser(userRole);
            if(qur!=null&&!qur.equals("")){
                jsonResult=new JsonResult(false,"该配置已经存在",qur);
            }else{
                Map<String,Object> params=super.getParamMap();
                int eru=roleSetService.editRoleUser(params);
                jsonResult=new JsonResult(true,"修改成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"修改失败");
        }
        return jsonResult;
    }

    /**
     * 修改角色权限设置
     * @param roleaction
     * @return
     */
    @ResponseBody
    @RequestMapping("editRoleAction")
    public JsonResult editRoleAction(roleAction roleaction){
        JsonResult jsonResult=null;
        try {
            roleAction qra=roleSetService.queryRoleAction(roleaction);
            if(qra!=null&&!qra.equals("")){
                jsonResult=new JsonResult(false,"该配置已经存在",qra);
            }else{
                Map<String,Object> params=super.getParamMap();
                int iru=roleSetService.editRoleAction(params);
                jsonResult=new JsonResult(true,"修改成功",iru);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"修改失败");
        }
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("editRoleMenu")
    public JsonResult editRoleMenu(RoleMenu roleMenu){
        JsonResult jsonResult=null;
        try {
            RoleMenu qrm=roleSetService.queryRoleMenu(roleMenu);
            if(qrm!=null&&!qrm.equals("")){
                jsonResult=new JsonResult(false,"该配置已经存在",qrm);
            }else{
                Map<String,Object> params=super.getParamMap();
                int iru=roleSetService.editRoleMenu(params);
                jsonResult=new JsonResult(true,"修改成功",iru);
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"修改失败");
        }
        return jsonResult;
    }
    /**
     * 删除用户角色配置
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteRoleUser")
    public JsonResult deleteRoleUser(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            int urId=Integer.parseInt(params.get("Id").toString());
            int dru=roleSetService.deleteRoleUser(urId);
            jsonResult=new JsonResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"删除失败");
        }
        return jsonResult;
    }

    /**
     * 删除角色权限配置
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteRoleAction")
    public JsonResult deleteRoleAction(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            int raId=Integer.parseInt(params.get("Id").toString());
            int dra=roleSetService.deleteRoleAction(raId);
            jsonResult=new JsonResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"删除失败");
        }
        return jsonResult;
    }

    /**
     * 删除角色菜单配置
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteRoleMenu")
    public JsonResult deleteRoleMenu(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            int rmId=Integer.parseInt(params.get("Id").toString());
            int dra=roleSetService.deleteRoleMenu(rmId);
            jsonResult=new JsonResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"删除失败");
        }
        return jsonResult;
    }
}
