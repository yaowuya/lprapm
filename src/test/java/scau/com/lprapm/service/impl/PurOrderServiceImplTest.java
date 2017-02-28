package scau.com.lprapm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by 钟锐锋 on 2017/2/8.
 */
public class PurOrderServiceImplTest extends BaseTest{
    @Autowired
    PurOrderServiceImpl purOrderService;
    @Test
    public void searchPO() throws Exception {
        Map<String,Object> params=new LinkedHashMap<>();
        params.put("goodsName","冰块");
        List<Map<String,Object>> list=purOrderService.searchPO(params);
        System.out.println(list);
    }

}