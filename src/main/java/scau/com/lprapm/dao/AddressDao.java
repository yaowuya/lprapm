package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Repository
public interface AddressDao {
    @Select("<script>" +
            " select provinceid,province " +
            " from provinces " +
            " where 1=1 " +
            " <if test=\"provinceid != null and provinceid != '' \">" +
            "    and provinceid= #{provinceid} " +
            " </if>" +
            "</script>")
    List<Map<String, Object>> province(Map<String, Object> params);

    @Select("<script>" +
            " select cityid,city " +
            " from cities " +
            " where 1=1 " +
            " <if test=\"cityid != null and cityid != '' \">" +
            "    and cityid= #{cityid} " +
            " </if>" +
            " <if test=\"provinceid != null and provinceid != '' \">" +
            "    and provinceid= #{provinceid} " +
            " </if>" +
            "</script>")
    List<Map<String, Object>> city(Map<String, Object> params);

    @Select("<script>" +
            " select areaid,area " +
            " from areas " +
            " where 1=1 " +
            " <if test=\"areaid != null and areaid != '' \">" +
            "    and areaid= #{areaid} " +
            " </if>" +
            " <if test=\"cityid != null and cityid != '' \">" +
            "    and cityid= #{cityid} " +
            " </if>" +
            "</script>")
    List<Map<String, Object>> area(Map<String, Object> params);
}
