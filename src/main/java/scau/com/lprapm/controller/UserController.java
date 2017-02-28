package scau.com.lprapm.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import scau.com.lprapm.common.Constant;
import scau.com.lprapm.common.JsonResult;
import scau.com.lprapm.common.VerifyCode;
import scau.com.lprapm.entity.User;
import scau.com.lprapm.entity.UserRole;
import scau.com.lprapm.exception.UserException;
import scau.com.lprapm.service.inter.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by zhongrf on 2016/12/13.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    UserService userService;
    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    @RequestMapping("/login")
    public JsonResult login(User user){
//        user.setUserEmail(request.getParameter("userEmail"));
//        user.setUserPassword(request.getParameter("userPassword"));
        JsonResult jsonResult=null;
        try{
           User current=userService.login(user.getUserEmail(),user.getUserPassword());
           request.getSession(true).setAttribute(Constant.CURRENR_USER,current);
            jsonResult=new JsonResult(true,"登录成功",current);
        }catch (UserException e){
//            e.printStackTrace();
            Map<String,Object> errorResult=new LinkedHashMap<>();
            errorResult.put("User",user);
            errorResult.put("msg",e.getMessage());
           jsonResult=new JsonResult(false,"loginError",errorResult);
        }catch (Exception e){
            e.printStackTrace();
            Map<String,Object> errorResult=new LinkedHashMap<>();
            errorResult.put("User",user);
            jsonResult=new JsonResult(false,"systemError",errorResult);
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("logOut")
    public JsonResult logOut(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult=null;
        try {
            request.getSession().removeAttribute(Constant.CURRENR_USER);
            request.getSession().removeAttribute("menuList");
            response.sendRedirect("/login.html");
            jsonResult=new JsonResult(true,"退出成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,"退出失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("/checkVerify")
    public JsonResult login(HttpSession session){
//        验证验证码是否正确
        JsonResult jsonResult=null;
        String VerifyCode="";
        Map<String,Object> param=super.getParamMap();
        Iterator iter = param.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if(entry.getKey().equals("checkCode")){
                VerifyCode=(String)entry.getValue();
            }
//            System.out.println(entry.getKey()+"  "+(String)entry.getValue());
        }
        String vc_code=(String)session.getAttribute("session_vCode");
        if(!vc_code.equalsIgnoreCase(VerifyCode)){
            jsonResult=new JsonResult(false,"验证码错误");
        }else{
            jsonResult=new JsonResult(true,"验证码正确");
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("/register")
    public JsonResult register(User user){
        JsonResult jsonResult=null;
        UserRole userRole=new UserRole();
        try{
            userRole.setRoleId(1);
            int regNum=userService.insertUser(user,userRole);
            jsonResult=new JsonResult(regNum,true,null);
        }catch (Exception e){
            e.printStackTrace();
            return jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("/verifycode")
    public String VerifyCode(HttpServletResponse response, HttpSession session){
//        产生验证码
        VerifyCode vc=new VerifyCode();
        BufferedImage image=vc.getImage();
        session.setAttribute("session_vCode",vc.getText());
        try{
            VerifyCode.output(image,response.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("searchUser")
    public JsonResult searchUser(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            List<Map<String,Object>> uList=userService.searchUser(params);
            jsonResult=new JsonResult(true,"查询成功",uList);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("insertUser")
    public JsonResult insertUser(User user){
        JsonResult jsonResult=null;
        UserRole userRole=new UserRole();
        try {
            userRole.setRoleId(1);
           int insert=userService.insertUser(user,userRole);
           return jsonResult=new JsonResult(true,"插入成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 当有日期传进来时，如果要用到entity，则需要进行日期转换，否则报错
     * @param binder
     * 另一种简单的方法是：
     * 在entity中
     * @DateTimeFormat(pattern = "yyyy-MM-dd")
     *private Date createTime;
     */
    @InitBinder
    public void InitBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }
    @ResponseBody
    @RequestMapping("editUser")
    public JsonResult editUser(User user){
        JsonResult jsonResult=null;
        try {
            int edit=userService.updateUser(user);
            jsonResult=new JsonResult(true,"修改成功",user);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("deleteUser")
    public JsonResult deleteUser(){
        JsonResult jsonResult=null;
        try {
            Map<String,Object> params=super.getParamMap();
            int userId=Integer.parseInt(params.get("userId").toString());
            int urId=Integer.parseInt(params.get("urId").toString());
            int delete=userService.deleteUser(userId,urId);
            jsonResult = new JsonResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonResult=new JsonResult(false,e.getMessage());
        }
        return jsonResult;
    }
}
