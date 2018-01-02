package com.lhfx.service;

import com.lhfx.entity.Cities;
import com.lhfx.dao.CitiesDao;
import com.youngo.exception.ErrcodeRuntimeException;
import com.youngo.utils.Pagination;
import com.youngo.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("citiesService")
public class CitiesService {
    
    private static final Logger logger=Logger.getLogger(CitiesService.class);

    @Autowired
    private CitiesDao citiesDao;

    public Cities get(Integer id){
        return citiesDao.getById(id);
    }

    public int delete(String[] ids){
        return citiesDao.deleteById(ids);
    }

    public int enable(String enable,String[] ids){
        Map<String, Object> params = new HashMap();
        params.put("validFlag",enable);
        params.put("ids",ids);
       return citiesDao.flagBatchId(params);
    }

    public int update(final Cities entity){
        return citiesDao.updateByEntity(entity);
    }

    public int batchUpdate(List<Cities> entity){
        return citiesDao.batchUpdateByEntity(entity);
    }

    public int add(final Cities entity){
        return citiesDao.insert(entity);
    }

    public int batchAdd(final List<Cities> list){
        return citiesDao.batchInsert(list);
    }

    public List<Cities> list(final Map<String, Object> params){
        return citiesDao.list(params);
    }

    public List<Map<String, String>> dict(Map<String, Object> params){
        return citiesDao.dict(params);
    }

    public Pagination page(Map<String, Object> params,int page,int pageSize){
        if (page < 1){
            page = 1;
        }
        if (pageSize < 1){
            pageSize = 1;
        }
        params.put("start",pageSize * (page -1));
        params.put("length",pageSize);

        return new Pagination(citiesDao.list(params),citiesDao.count(params),page,pageSize);
    }

}