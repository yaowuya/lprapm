package scau.com.lprapm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/1/8.
 */
public class ActionServiceImplTest extends BaseTest{
    @Autowired
    ActionServiceImpl actionService;
    @Test
    public void deleteAction() throws Exception {
        int actionId=3;
        int delete=actionService.deleteAction(actionId);
    }

}