package scau.com.lprapm.service.inter;

import scau.com.lprapm.entity.LogPrice;
import scau.com.lprapm.entity.OrderExam;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/12.
 */
public interface LogisticService {
    List<Map<String, Object>> searchExamLog(Map<String, Object> params);

    void checkExamLog(OrderExam orderExam);

    void revokeExamLog(Map<String, Object> params);

    void checkReplyLog(LogPrice logPrice);

    void revokeReplyLog(Map<String, Object> params);
}
