package com.lhfx.service;

import com.lhfx.entity.CraftsmanSkill;
import com.lhfx.dao.CraftsmanSkillDao;
import com.youngo.exception.ErrcodeRuntimeException;
import com.youngo.utils.Pagination;
import com.youngo.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("craftsmanSkillService")
public class CraftsmanSkillService {
    
    private static final Logger logger=Logger.getLogger(CraftsmanSkillService.class);

    @Autowired
    private CraftsmanSkillDao craftsmanSkillDao;


    public int add(final CraftsmanSkill entity){
        return craftsmanSkillDao.insert(entity);
    }

    public int batchAdd(final List<CraftsmanSkill> list){
        return craftsmanSkillDao.batchInsert(list);
    }

    public List<CraftsmanSkill> list(final Map<String, Object> params){
        return craftsmanSkillDao.list(params);
    }

    public List<Map<String, String>> dict(Map<String, Object> params){
        return craftsmanSkillDao.dict(params);
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

        return new Pagination(craftsmanSkillDao.list(params),craftsmanSkillDao.count(params),page,pageSize);
    }

}