package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.CarNeedMapper;
import scau.com.lprapm.dao.CarPlanMapper;
import scau.com.lprapm.dao.PositionTrackingMapper;
import scau.com.lprapm.entity.CarNeed;
import scau.com.lprapm.entity.CarPlan;
import scau.com.lprapm.entity.PositionTracking;
import scau.com.lprapm.service.inter.CarSService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/19.
 */
@Service
public class CarSServiceImpl implements CarSService {
    @Autowired
    CarPlanMapper carPlanMapper;
    @Autowired
    CarNeedMapper carNeedMapper;
    @Autowired
    PositionTrackingMapper positionTrackingMapper;

    @Override
    public List<Map<String, Object>> searchCarS(Map<String, Object> params) {
        return carPlanMapper.searchCarS(params);
    }

    @Override
    public void updateCarS(CarPlan carPlan, CarNeed carNeed) {
        try {
            String[] orderIds = carPlan.getOrderIds().split(",");
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("provinceid", carPlan.getProvinceid());
            map.put("cityid", carPlan.getCityid());
            map.put("areaid", carPlan.getAreaid());
            map.put("trackStatus", "出发");
            carNeedMapper.insertCarNeed(carNeed);
            carPlan.setCarnId(carNeed.getCarnId());
            carPlanMapper.insertCarPlan(carPlan);
            map.put("carplanId", carPlan.getCarplanId());
            for (String str : orderIds) {
                map.put("orderId", Integer.parseInt(str));
                positionTrackingMapper.insertData(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
