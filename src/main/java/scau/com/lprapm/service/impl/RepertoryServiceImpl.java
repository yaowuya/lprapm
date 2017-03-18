package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.RepertoryMapper;
import scau.com.lprapm.entity.Repertory;
import scau.com.lprapm.service.inter.RepertoryService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/18.
 */
@Service
public class RepertoryServiceImpl implements RepertoryService {
    @Autowired
    RepertoryMapper repertoryMapper;

    @Override
    public List<Map<String, Object>> searchReper(Map<String, Object> params) {
        return repertoryMapper.searchReper(params);
    }

    @Override
    public void insertReper(Repertory repertory) {
        repertoryMapper.insert(repertory);
    }

    @Override
    public void updateReper(Repertory repertory) {
        repertoryMapper.updateByPrimaryKeySelective(repertory);
    }

    @Override
    public void deleteReper(int repoId) {
        repertoryMapper.deleteByPrimaryKey(repoId);
    }
}
