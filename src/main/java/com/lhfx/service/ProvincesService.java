package com.lhfx.service;

import com.lhfx.entity.Provinces;
import com.lhfx.dao.ProvincesDao;
import com.youngo.exception.ErrcodeRuntimeException;
import com.youngo.utils.Pagination;
import com.youngo.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("provincesService")
public class ProvincesService {
    
    private static final Logger logger=Logger.getLogger(ProvincesService.class);

    @Autowired
    private ProvincesDao provincesDao;

    public Provinces get(Integer id){
        return provincesDao.getById(id);
    }

    public int delete(String[] ids){
        return provincesDao.deleteById(ids);
    }

    public int enable(String enable,String[] ids){
        Map<String, Object> params = new HashMap();
        params.put("validFlag",enable);
        params.put("ids",ids);
       return provincesDao.flagBatchId(params);
    }

    public int update(final Provinces entity){
        return provincesDao.updateByEntity(entity);
    }

    public int batchUpdate(List<Provinces> entity){
        return provincesDao.batchUpdateByEntity(entity);
    }

    public int add(final Provinces entity){
        return provincesDao.insert(entity);
    }

    public int batchAdd(final List<Provinces> list){
        return provincesDao.batchInsert(list);
    }

    public List<Provinces> list(final Map<String, Object> params){
        return provincesDao.list(params);
    }

    public List<Map<String, String>> dict(Map<String, Object> params){
        return provincesDao.dict(params);
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

        return new Pagination(provincesDao.list(params),provincesDao.count(params),page,pageSize);
    }

}