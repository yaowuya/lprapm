package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.Car;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/13.
 */
public interface VehicleService {
    List<Map<String, Object>> searchUser();

    List<Map<String, Object>> searchVehicle(Map<String, Object> params);

    void insertVehicle(Car car);

    void updateVehicle(Car car);

    void deleteVehicle(int carId);
}
