package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.Action;
import scau.com.lprapm.entity.Role;
import scau.com.lprapm.entity.roleAction;
import scau.com.lprapm.service.inter.ActionService;
import scau.com.lprapm.service.inter.RoleService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/11.
 */
@Controller
@RequestMapping("/roleAction")
public class RoleActionController extends BaseController{
    @Autowired
    RoleService roleService;
    @Autowired
    ActionService actionService;

    @ResponseBody
    @RequestMapping("searchRole")
    public JsonResult searchRoles(){
//        角色查询功能
        JsonResult jsonResult=null;
        try{
            Map<String,Object> params=super.getParamMap();
            List<Map<String,Object>> rList=roleService.searchRoles(params);
            jsonResult=new JsonResult(true,"查询成功",rList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("insertRole")
    public JsonResult insertRole(Role role){
//        插入角色
        JsonResult jsonResult=null;
        try {
            roleAction roleaction = new roleAction();
            roleaction.setActionId(1);
            int insert=roleService.insertRole(role,roleaction);
            jsonResult=new JsonResult(true,"插入成功",role);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("editRole")
    public JsonResult editRole(Role role){
        JsonResult jsonResult=null;
        try {
            int edit=roleService.updateRole(role);
            jsonResult=new JsonResult(true,"修改成功",role);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("deleteRole")
    public JsonResult deleteRole(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            int roleId=Integer.parseInt(params.get("roleId").toString());
            int raId=Integer.parseInt(params.get("raId").toString());
            int delete=roleService.deleteRole(roleId,raId);
            jsonResult=new JsonResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("searchAction")
    public JsonResult searchAction(){
        JsonResult jsonResult=null;
        try{
            Map<String,Object> params=super.getParamMap();
            List<Map<String,Object>> aList=actionService.searchAction(params);
            jsonResult=new JsonResult(true,"查询成功",aList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("insertAction")
    public JsonResult insertAction(Action action){
        JsonResult jsonResult=null;
        try {
            int insert=actionService.insertAction(action);
            jsonResult=new JsonResult(true,"插入成功",action);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("editAction")
    public JsonResult editAction(Action action){
        JsonResult jsonResult=null;
        try {
            int edit=actionService.editAction(action);
            jsonResult=new JsonResult(true,"编辑成功",action);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("deleteAction")
    public JsonResult deleteAction(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            int actionId=Integer.parseInt(params.get("actionId").toString());
            int delete=actionService.deleteAction(actionId);
            jsonResult=new JsonResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }
}
