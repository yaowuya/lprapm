package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.Car;
import scau.com.lprapm.entity.CarType;

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

    List<Map<String, Object>> searchCarType(Map<String, Object> params);

    void insertCarType(CarType carType);

    void updateCarType(CarType carType);

    void deleteCarType(int ctId);
}
