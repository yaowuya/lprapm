package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.Action;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/7.
 */
public interface ActionService {
    List<Map<String,Object>> searchAction(Map<String, Object> params);

    int insertAction(Action action);

    int editAction(Action action);

    int deleteAction(int actionId);
}
