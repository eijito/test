package com.lhfx.service;

import com.lhfx.entity.Areas;
import com.lhfx.dao.AreasDao;
import com.youngo.exception.ErrcodeRuntimeException;
import com.youngo.utils.Pagination;
import com.youngo.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("areasService")
public class AreasService {
    
    private static final Logger logger=Logger.getLogger(AreasService.class);

    @Autowired
    private AreasDao areasDao;

    public Areas get(Integer id){
        return areasDao.getById(id);
    }

    public int delete(String[] ids){
        return areasDao.deleteById(ids);
    }

    public int enable(String enable,String[] ids){
        Map<String, Object> params = new HashMap();
        params.put("validFlag",enable);
        params.put("ids",ids);
       return areasDao.flagBatchId(params);
    }

    public int update(final Areas entity){
        return areasDao.updateByEntity(entity);
    }

    public int batchUpdate(List<Areas> entity){
        return areasDao.batchUpdateByEntity(entity);
    }

    public int add(final Areas entity){
        return areasDao.insert(entity);
    }

    public int batchAdd(final List<Areas> list){
        return areasDao.batchInsert(list);
    }

    public List<Areas> list(final Map<String, Object> params){
        return areasDao.list(params);
    }

    public List<Map<String, String>> dict(Map<String, Object> params){
        return areasDao.dict(params);
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

        return new Pagination(areasDao.list(params),areasDao.count(params),page,pageSize);
    }

}