package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.ActionMapper;
import scau.com.lprapm.dao.roleActionMapper;
import scau.com.lprapm.entity.Action;
import scau.com.lprapm.entity.roleAction;
import scau.com.lprapm.service.inter.ActionService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/7.
 */
@Service
public class ActionServiceImpl implements ActionService{
    @Autowired
    ActionMapper actionMapper;
    @Autowired
    roleActionMapper roleactionMapper;

    @Override
    public List<Map<String, Object>> searchAction(Map<String, Object> params) {
        try{
            List<Map<String, Object>> aList=actionMapper.searchAction(params);
            return aList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertAction(Action action) {
        try {
            int insert=actionMapper.insert(action);
            return insert;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int editAction(Action action) {
        try {
            int edit=actionMapper.updateByPrimaryKey(action);
            return edit;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteAction(int actionId) {
        try {
            int deletea=actionMapper.deleteByPrimaryKey(actionId);
            int updatea=roleactionMapper.updateByActionId(actionId);
            return deletea;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
