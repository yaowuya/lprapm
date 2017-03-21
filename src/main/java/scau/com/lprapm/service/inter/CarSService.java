package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.CarNeed;
import scau.com.lprapm.entity.CarPlan;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/19.
 */
public interface CarSService {
    List<Map<String, Object>> searchCarS(Map<String, Object> params);

    void updateCarS(CarPlan carPlan, CarNeed carNeed);

    void surePOS(Map<String, Object> params);

    List<Map<String, Object>> searchPOS(Map<String, Object> params);
}
