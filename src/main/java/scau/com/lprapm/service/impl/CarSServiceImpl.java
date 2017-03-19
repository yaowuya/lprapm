package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.CarPlanMapper;
import scau.com.lprapm.service.inter.CarSService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/19.
 */
@Service
public class CarSServiceImpl implements CarSService {
    @Autowired
    CarPlanMapper carPlanMapper;

    @Override
    public List<Map<String, Object>> searchCarS(Map<String, Object> params) {
        return carPlanMapper.searchCarS(params);
    }
}
