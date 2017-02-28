package scau.com.lprapm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 链接
 * Created by Administrator on 2017/1/11.
 */
@Controller
@RequestMapping("/redirect")
public class UrlController extends BaseController {

    @Autowired
    HttpServletRequest request;
    /**
     * 登录成功之后跳转到index页面
     * @return
     */
    @ResponseBody
    @RequestMapping("index")
    public ModelAndView redirect(){
        ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }

    /**
     * 左边菜单栏跳转链接
     * @return
     */
    @ResponseBody
    @RequestMapping("page/**/")
    public ModelAndView page(){
        String requestURI=request.getRequestURI();
        String path=requestURI.substring("/redirect/page/".length());
        return new ModelAndView("/"+path);
    }
}
