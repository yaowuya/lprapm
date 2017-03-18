package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.CarType;

import java.util.List;
import java.util.Map;

@Repository
public interface CarTypeMapper {
    int deleteByPrimaryKey(Integer ctId);

    int insert(CarType record);

    int insertSelective(CarType record);

    CarType selectByPrimaryKey(Integer ctId);

    int updateByPrimaryKeySelective(CarType record);

    int updateByPrimaryKey(CarType record);

    @Select("<script>" +
            "select ct_id ctId, ct_type ctType, ct_volume ctVolume," +
            " ct_weight ctWeight, km_price kmPrice " +
            "from car_type " +
            "where 1=1 " +
            "<if test=\"ctType != null and ctType != '' \">" +
            " and ct_type=#{ctType}" +
            "</if>" +
            "</script>")
    List<Map<String, Object>> searchCarType(Map<String, Object> params);
}