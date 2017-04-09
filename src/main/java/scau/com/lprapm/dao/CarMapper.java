package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.Car;

import java.util.List;
import java.util.Map;

@Repository
public interface CarMapper {
    int deleteByPrimaryKey(Integer carId);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer carId);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    @Select("<script>" +
            "select c.car_id carId, c.user_id userId,u.user_true_name userTrueName," +
            " c.car_license carLicense, " +
            " c.car_type carType, c.car_volume carVolume, c.car_weight carWeight, " +
            " c.km_price kmPrice, c.is_free isFree " +
            "from car c,user u " +
            "where c.user_id = u.user_id " +
            " <if test=\"carType != null and carType != '' \">" +
            "    and car_type like concat('%',#{carType},'%')" +
            " </if>" +
            " <if test=\"carLicense != null and carLicense != '' \">" +
            "    and car_license= #{carLicense} " +
            " </if>" +
            " <if test=\"isFree != null and isFree != '' \">" +
            "    and is_free= #{isFree} " +
            " </if>" +
            "</script>")
    List<Map<String, Object>> searchVehicle(Map<String, Object> params);

    @Select("<script>" +
            "select c.car_id carId, c.user_id userId,u.user_true_name userTrueName," +
            " c.car_license carLicense, " +
            " c.car_type carType, c.car_volume carVolume, c.car_weight carWeight, " +
            " c.km_price kmPrice, c.is_free isFree " +
            "from car c,user u " +
            "where c.user_id = u.user_id " +
            " <if test=\"carWeight != null and carWeight != '' \">" +
            "    and car_weight= #{carWeight} " +
            " </if>" +
            " <if test=\"carType != null and carType != '' \">" +
            "    and car_type like concat('%',#{carType},'%')" +
            " </if>" +
            " <if test=\"isFree != null and isFree != '' \">" +
            "    and is_free= #{isFree} " +
            " </if>" +
            " LIMIT ${number} " +
            "</script>")
    List<Map<String, Object>> searchCarNeed(Map<String, Object> params);



}