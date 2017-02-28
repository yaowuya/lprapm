package scau.com.lprapm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import scau.com.lprapm.common.Constant;
import scau.com.lprapm.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/12.
 */
public class LoginInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        //获取请求的url
        String url = request.getRequestURI();

        //获取路径，用于转发
        String path = request.getContextPath();

        //获取登录时存在session里面的user
        User currentUser=(User)request.getSession().getAttribute(Constant.CURRENR_USER);

        /**
         * 对一些登陆前应该允许的请求放行
         * 特殊请求包括
         * 	1.login:登陆请求
         * 	2.regist:注册
         * 	3.verify:验证码
         * 	4.menu:菜单的自动生成
         * 	5.check:注册验证
         */
//        if(url.contains("login") || url.contains("register") || url.contains("verify") || url.contains("check")||url.contains("favicon.io")){
//            return true;
//        }
//        if(currentUser==null){
//            response.sendRedirect(path  + "/login.html");
//        }else{
//            if(url.contains("index")) return true;
//            if(url.contains("searchLeftMenu")) return true;
//            if(url.contains("logOut")) return true;
//            List<Map<String,Object>> menuList=(List<Map<String,Object>>)request.getSession().getAttribute("menuList");
////            System.out.println("menuList:"+menuList);
//            for(Map<String,Object> map:menuList){
////                System.out.println("url:"+url+"   href:"+map.get("menuHref").toString().substring("/redirect/page/".length()));
//                if(url.contains(map.get("menuHref").toString().substring("/redirect/page/".length()))){
//                    return true;
//                }
//            }
//            //如果没有遍历到则表示该用户没有相关权限，跳转至index.html
//            response.sendRedirect("/login.html");
//        }
//        return false;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public void setIgnorePath(String ignorePath) {
    }
}
