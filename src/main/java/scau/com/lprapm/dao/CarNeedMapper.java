package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.CarNeed;
import scau.com.lprapm.entity.CarPlan;

import java.util.List;
import java.util.Map;

@Repository
public interface CarNeedMapper {
    int deleteByPrimaryKey(Integer carnId);

    int insert(CarNeed record);

    int insertSelective(CarNeed record);

    CarNeed selectByPrimaryKey(Integer carnId);

    int updateByPrimaryKeySelective(CarNeed record);

    int updateByPrimaryKey(CarNeed record);

    void insertCarNeed(CarNeed carNeed);

    @Select("<script>" +
            "select carn_id carnId, carn_type carnType, carn_num carnNum, " +
            "carn_exam_state carnExamState, car_exam_person carExamPerson, " +
            "carn_exam_dept carnExamDept, car_ids carIds " +
            "from car_need " +
            "where 1=1 " +
            " <if test=\"carnExamState != null and carnExamState != '' \">" +
            "    and carn_exam_state in ( ${carnExamState} ) " +
            " </if>" +
            "</script>")
    List<Map<String, Object>> searchVDemand(Map<String, Object> params);
}