package com.lhfx.service;

import com.lhfx.dao.CraftsmanDao;
import com.lhfx.entity.Craftsman;
import com.youngo.exception.ErrcodeRuntimeException;
import com.youngo.utils.Pagination;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("craftsmanService")
public class CraftsmanService {
    
    private static final Logger logger=Logger.getLogger(CraftsmanService.class);

    @Autowired
    private CraftsmanDao craftsmanDao;

    public Craftsman get(Integer id){
        return craftsmanDao.getById(id);
    }

    public int delete(String[] ids){
        if(craftsmanDao.linkedCraftsmanSkill(ids) > 0){
            throw new ErrcodeRuntimeException(ErrcodeRuntimeException.BUSINESS_ERR,"该数据已被[工匠技能]引用，不能删除");
        }
        return craftsmanDao.deleteById(ids);
    }

    public int enable(String enable,String[] ids){
        Map<String, Object> params = new HashMap();
        params.put("validFlag",enable);
        params.put("ids",ids);
       return craftsmanDao.flagBatchId(params);
    }

    public int update(final Craftsman entity){
        craftsmanDao.deleteLinkedCraftsmanSkill(String.valueOf(entity.getId()));
        craftsmanDao.insertCraftsmanSkillIfMiss(entity.getId(),entity.getCraftsmanSkillList());
        return craftsmanDao.updateByEntity(entity);
    }

    public int batchUpdate(List<Craftsman> entity){
        return craftsmanDao.batchUpdateByEntity(entity);
    }

    public int add(final Craftsman entity){
        int ret = craftsmanDao.insert(entity);
        craftsmanDao.insertCraftsmanSkillIfMiss(entity.getId(),entity.getCraftsmanSkillList());
        return ret;
    }

    public int batchAdd(final List<Craftsman> list){
        return craftsmanDao.batchInsert(list);
    }

    public List<Craftsman> list(final Map<String, Object> params){
        return craftsmanDao.list(params);
    }

    public List<Map<String, String>> dict(Map<String, Object> params){
        return craftsmanDao.dict(params);
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

        return new Pagination(craftsmanDao.list(params),craftsmanDao.count(params),page,pageSize);
    }

}