package scau.com.lprapm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.com.lprapm.dao.AddressDao;
import scau.com.lprapm.service.inter.AddressService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressDao addressDao;

    @Override
    public List<Map<String, Object>> province(Map<String, Object> params) {
        return addressDao.province(params);
    }

    @Override
    public List<Map<String, Object>> city(Map<String, Object> params) {
        return addressDao.city(params);
    }

    @Override
    public List<Map<String, Object>> area(Map<String, Object> params) {
        return addressDao.area(params);
    }
}
