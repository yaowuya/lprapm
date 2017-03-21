package scau.com.lprapm.service.inter;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/20.
 */
public interface PositionService {

    void insertPOS(Map<String, Object> params);

    List<Map<String, Object>> queryTrack(Map<String, Object> params);
}
