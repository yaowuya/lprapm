package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.entity.Department;
import scau.com.lprapm.service.inter.DepartmentService;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/1/10.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {
    @Autowired
    DepartmentService departmentService;

    @ResponseBody
    @RequestMapping("searchDept")
    public JsonResult searchDept(){
       JsonResult jsonResult=null;
       try {
           Map<String ,Object> params=super.getParamMap();
           List<Map<String ,Object>> dList=departmentService.searchDept(params);
           jsonResult=new JsonResult(true,"查询成功",dList);
       }catch (Exception e){
           e.printStackTrace();
           jsonResult=new JsonResult(false,e.getMessage());
       }
       return jsonResult;
    }

    @ResponseBody
    @RequestMapping("insertDept")
    public JsonResult insertDept(Department department){
       JsonResult jsonResult=null;
       try {
           int insert=departmentService.insertDept(department);
           jsonResult=new JsonResult(true,"插入成功");
       }catch (Exception e){
           e.printStackTrace();
           jsonResult=new JsonResult(false,e.getMessage());
       }
       return jsonResult;
    }
    @ResponseBody
    @RequestMapping("editDept")
    public JsonResult editDept(Department department){
        JsonResult jsonResult=null;
        try {
            int edit=departmentService.updateDept(department);
            jsonResult=new JsonResult(true,"编辑成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("deleteDept")
    public JsonResult deleteDept(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            int deptId=Integer.parseInt(params.get("deptId").toString());
            int delete=departmentService.deleteDept(deptId);
            jsonResult=new JsonResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }
}
