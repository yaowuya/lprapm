package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.Repertory;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/18.
 */
public interface RepertoryService {
    List<Map<String, Object>> searchReper(Map<String, Object> params);

    void insertReper(Repertory repertory);

    void updateReper(Repertory repertory);

    void deleteReper(int repoId);
}
