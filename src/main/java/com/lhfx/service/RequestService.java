package com.lhfx.service;

import com.lhfx.entity.Request;
import com.lhfx.dao.RequestDao;
import com.youngo.exception.ErrcodeRuntimeException;
import com.youngo.utils.Pagination;
import com.youngo.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("requestService")
public class RequestService {
    
    private static final Logger logger=Logger.getLogger(RequestService.class);

    @Autowired
    private RequestDao requestDao;


    public int add(final Request entity){
        return requestDao.insert(entity);
    }

    public int batchAdd(final List<Request> list){
        return requestDao.batchInsert(list);
    }

    public List<Request> list(final Map<String, Object> params){
        return requestDao.list(params);
    }

    public List<Map<String, String>> dict(Map<String, Object> params){
        return requestDao.dict(params);
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

        return new Pagination(requestDao.list(params),requestDao.count(params),page,pageSize);
    }

}