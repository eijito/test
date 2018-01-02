package com.lhfx.service;

import com.lhfx.entity.Company;
import com.lhfx.dao.CompanyDao;
import com.youngo.exception.ErrcodeRuntimeException;
import com.youngo.utils.Pagination;
import com.youngo.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("companyService")
public class CompanyService {
    
    private static final Logger logger=Logger.getLogger(CompanyService.class);

    @Autowired
    private CompanyDao companyDao;

    public Company get(Integer id){
        return companyDao.getById(id);
    }

    public int delete(String[] ids){
        if(companyDao.linkedRequest(ids) > 0){
            throw new ErrcodeRuntimeException(ErrcodeRuntimeException.BUSINESS_ERR,"该数据已被[需求]引用，不能删除");
        }
        return companyDao.deleteById(ids);
    }

    public int enable(String enable,String[] ids){
        Map<String, Object> params = new HashMap();
        params.put("validFlag",enable);
        params.put("ids",ids);
       return companyDao.flagBatchId(params);
    }

    public int update(final Company entity){
        return companyDao.updateByEntity(entity);
    }

    public int batchUpdate(List<Company> entity){
        return companyDao.batchUpdateByEntity(entity);
    }

    public int add(final Company entity){
        return companyDao.insert(entity);
    }

    public int batchAdd(final List<Company> list){
        return companyDao.batchInsert(list);
    }

    public List<Company> list(final Map<String, Object> params){
        return companyDao.list(params);
    }

    public List<Map<String, String>> dict(Map<String, Object> params){
        return companyDao.dict(params);
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

        return new Pagination(companyDao.list(params),companyDao.count(params),page,pageSize);
    }

}