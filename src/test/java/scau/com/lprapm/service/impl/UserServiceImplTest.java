package scau.com.lprapm.service.impl;

import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import scau.com.lprapm.entity.User;
import scau.com.lprapm.entity.UserRole;
import sun.rmi.runtime.Log;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by 钟锐锋 on 2016/12/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml","classpath:spring-context.xml"})
@WebAppConfiguration
public class UserServiceImplTest {

    //    private static final Logger logger = LogManager.getLogger(UserServiceImplTest.class);
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserServiceImpl userService;
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void insertUser() throws Exception {
        User user=new User();
        UserRole userRole=new UserRole();
        user.setUserEmail("2222@222");
        user.setUserPassword("12345");
        userRole.setRoleId(1);
        int a=userService.insertUser(user,userRole);
        System.out.println(user.getUserId());
    }

    @org.junit.Test
    public void login() throws Exception {
        User user=userService.login("111@112","12345");
        System.out.println(user.getUserEmail());
    }

    @Test
    public void insertMap() throws Exception {
        Map<String,Object> map=new LinkedHashMap<>();
        map.put("userEmail","11@1123");
        map.put("userPassword","123453");
        userService.insertMap(map);
        System.out.println(map.get("userId"));
    }

    @Test
    public void insertMap2() throws Exception {
        User user = new User();
        user.setUserEmail("33@22");
        user.setUserPassword("123");
        System.out.println(user);
        userService.insertMap2(user);
        System.out.println("t:"+user.getUserId());
    }

}