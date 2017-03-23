package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.common.Constant;
import scau.com.lprapm.dao.*;
import scau.com.lprapm.entity.CarNeed;
import scau.com.lprapm.entity.CarPlan;
import scau.com.lprapm.entity.PositionTracking;
import scau.com.lprapm.service.inter.CarSService;

import javax.servlet.http.HttpServletRequest;
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
    OrdersMapper ordersMapper;
    @Autowired
    PositionTrackingMapper positionTrackingMapper;
    @Autowired
    AddressDao addressDao;
    @Autowired
    HttpServletRequest request;

    @Override
    public List<Map<String, Object>> searchCarS(Map<String, Object> params) {
        return carPlanMapper.searchCarS(params);
    }

    @Override
    public void updateCarS(CarPlan carPlan, CarNeed carNeed) {
        try {
            Map<String, Object> map = new LinkedHashMap<>();
            String[] orderIds = carPlan.getOrderIds().split(",");
            carNeedMapper.insertCarNeed(carNeed);
            carPlan.setCarnId(carNeed.getCarnId());
            carPlanMapper.insertCarPlan(carPlan);
            for (String str : orderIds) {
                int id = Integer.parseInt(str);
                map.put("orderId", id);
                ordersMapper.editLogState(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surePOS(Map<String, Object> params) {
        Map<String, Object> map = new LinkedHashMap<>();
        Map<String, Object> addrMap = (Map) request.getSession().getAttribute(Constant.CURRENR_ADDR);
        CarNeed carNeed = new CarNeed();
        int carnId = Integer.parseInt(params.get("carnId").toString());
        carNeed.setCarnId(carnId);
        carNeed.setCarnExamState("出发");
        carNeedMapper.updateByPrimaryKeySelective(carNeed);
        String provinceid = addressDao.getProvinceid(addrMap.get("province").toString());
        String cityid = addressDao.getCityid(addrMap.get("city").toString());
        String areaid = addressDao.getAreaid(addrMap.get("area").toString());
        map.put("provinceid", provinceid);
        map.put("cityid", cityid);
        map.put("areaid", areaid);
        map.put("carnId", carnId);
        map.put("trackStatus", "出发");
        String[] orderIds = params.get("orderIds").toString().split(",");
        for (String str : orderIds) {
            int id = Integer.parseInt(str);
            map.put("orderId", id);
            ordersMapper.updateLogState(map);
            positionTrackingMapper.insertData(map);
        }
    }

    @Override
    public List<Map<String, Object>> searchPOS(Map<String, Object> params) {
        return carPlanMapper.searchPOS(params);
    }
}
