package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.CarMapper;
import scau.com.lprapm.dao.CarNeedMapper;
import scau.com.lprapm.dao.CarTypeMapper;
import scau.com.lprapm.dao.UserMapper;
import scau.com.lprapm.entity.Car;
import scau.com.lprapm.entity.CarNeed;
import scau.com.lprapm.entity.CarType;
import scau.com.lprapm.service.inter.VehicleService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/13.
 */
@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CarMapper carMapper;
    @Autowired
    CarTypeMapper carTypeMapper;
    @Autowired
    CarNeedMapper carNeedMapper;

    @Override
    public List<Map<String, Object>> searchUser() {
        return userMapper.searchAllUser();
    }

    @Override
    public List<Map<String, Object>> searchVehicle(Map<String, Object> params) {
        return carMapper.searchVehicle(params);
    }

    @Override
    public void insertVehicle(Car car) {
        carMapper.insert(car);
    }

    @Override
    public void updateVehicle(Car car) {
        carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public void deleteVehicle(int carId) {
        carMapper.deleteByPrimaryKey(carId);
    }

    @Override
    public List<Map<String, Object>> searchCarType(Map<String, Object> params) {
        return carTypeMapper.searchCarType(params);
    }

    @Override
    public void insertCarType(CarType carType) {
        carTypeMapper.insert(carType);
    }

    @Override
    public void updateCarType(CarType carType) {
        carTypeMapper.updateByPrimaryKeySelective(carType);
    }

    @Override
    public void deleteCarType(int ctId) {
        carTypeMapper.deleteByPrimaryKey(ctId);
    }

    @Override
    public List<Map<String, Object>> searchVDemand(Map<String, Object> params) {
        return carNeedMapper.searchVDemand(params);
    }

    @Override
    public List<Map<String, Object>> searchCarNeed(Map<String, Object> params) {
        return carMapper.searchCarNeed(params);
    }

    @Override
    public void insertVDemand(CarNeed carNeed) {
        carNeedMapper.updateByPrimaryKeySelective(carNeed);
        Car car = new Car();
        String[] carIds = carNeed.getCarIds().split(",");
        for (String id : carIds) {
            car.setCarId(Integer.parseInt(id));
            car.setIsFree("否");
            carMapper.updateByPrimaryKeySelective(car);
        }
    }
}
