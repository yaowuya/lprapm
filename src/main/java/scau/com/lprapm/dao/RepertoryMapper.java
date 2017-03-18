package scau.com.lprapm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.Repertory;

import java.util.List;
import java.util.Map;

@Repository
public interface RepertoryMapper {
    int deleteByPrimaryKey(Integer repoId);

    int insert(Repertory record);

    int insertSelective(Repertory record);

    Repertory selectByPrimaryKey(Integer repoId);

    int updateByPrimaryKeySelective(Repertory record);

    int updateByPrimaryKey(Repertory record);

    @Select("<script>" +
            "select r.repo_id repoId,r.areaid,a.area, r.cityid,c.city, " +
            "r.provinceid,p.province,r.repo_address repoAddress " +
            "from repertory r,provinces p,cities c,areas a " +
            "where r.provinceid=p.provinceid and r.cityid=c.cityid and r.areaid=a.areaid " +
            "<if test=\"provinceid != null and provinceid != '' \">" +
            " and r.provinceid = #{provinceid}" +
            "</if>" +
            "<if test=\"cityid != null and cityid != '' \">" +
            " and r.cityid = #{cityid}" +
            "</if>" +
            "<if test=\"areaid != null and areaid != '' \">" +
            " and a.areaid = #{areaid}" +
            "</if>" +
            "</script>")
    List<Map<String, Object>> searchReper(Map<String, Object> params);
}