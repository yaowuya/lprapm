package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.LogPriceMapper;
import scau.com.lprapm.dao.OrderExamMapper;
import scau.com.lprapm.entity.LogPrice;
import scau.com.lprapm.entity.OrderExam;
import scau.com.lprapm.service.inter.LogisticService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/12.
 */
@Service
public class LogisticServiceImpl implements LogisticService {
    @Autowired
    OrderExamMapper orderExamMapper;
    @Autowired
    LogPriceMapper logPriceMapper;

    @Override
    public List<Map<String, Object>> searchExamLog(Map<String, Object> params) {
        return orderExamMapper.searchExamLog(params);
    }

    @Override
    public void checkExamLog(OrderExam orderExam) {
        orderExamMapper.updateByPrimaryKeySelective(orderExam);
    }

    @Override
    public void revokeExamLog(Map<String, Object> params) {
        OrderExam orderExam = new OrderExam();
        orderExam.setOeId(Integer.parseInt(params.get("oeId").toString()));
        orderExam.setOeDept(null);
        orderExam.setOePerson(null);
        orderExam.setOeReason(null);
        orderExam.setOeState("审核中");
        orderExamMapper.updateByPrimaryKey(orderExam);
    }

    @Override
    public void checkReplyLog(LogPrice logPrice) {
        logPriceMapper.updateByPrimaryKeySelective(logPrice);
    }

    @Override
    public void revokeReplyLog(Map<String, Object> params) {
        LogPrice logPrice = new LogPrice();
        logPrice.setLogId(Integer.parseInt(params.get("logId").toString()));
        logPrice.setLogDept(null);
        logPrice.setLogPrice(null);
        logPrice.setLogPerson(null);
        logPrice.setLogState("已回复");
        logPriceMapper.updateByPrimaryKey(logPrice);
    }
}
