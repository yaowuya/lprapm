package scau.com.lprapm.service.inter;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/19.
 */
public interface CarSService {
    List<Map<String, Object>> searchCarS(Map<String, Object> params);
}