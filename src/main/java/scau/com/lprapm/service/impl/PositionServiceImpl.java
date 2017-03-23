package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.CarNeedMapper;
import scau.com.lprapm.dao.CarPlanMapper;
import scau.com.lprapm.dao.OrdersMapper;
import scau.com.lprapm.dao.PositionTrackingMapper;
import scau.com.lprapm.entity.CarNeed;
import scau.com.lprapm.entity.CarPlan;
import scau.com.lprapm.entity.Orders;
import scau.com.lprapm.service.inter.PositionService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/20.
 */
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    CarPlanMapper carPlanMapper;
    @Autowired
    CarNeedMapper carNeedMapper;
    @Autowired
    PositionTrackingMapper positionTrackingMapper;

    @Override
    public void insertPOS(Map<String, Object> params) {
        String[] orderIds = params.get("orderIds").toString().split(",");
        CarNeed carNeed = new CarNeed();
        int carnId = Integer.parseInt(params.get("carnId").toString());
        carNeed.setCarnExamState("到站");
        carNeed.setCarnId(carnId);
        carNeedMapper.updateByPrimaryKeySelective(carNeed);
        for (String str : orderIds) {
            params.put("orderId", Integer.parseInt(str));
            positionTrackingMapper.insertData(params);
        }
    }

    @Override
    public List<Map<String, Object>> queryTrack(Map<String, Object> params) {
        return positionTrackingMapper.queryTrack(params);
    }
}
