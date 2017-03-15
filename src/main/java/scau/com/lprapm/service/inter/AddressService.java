package scau.com.lprapm.service.inter;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface AddressService {
    List<Map<String, Object>> province(Map<String, Object> params);

    List<Map<String, Object>> city(Map<String, Object> params);

    List<Map<String, Object>> area(Map<String, Object> params);
}
