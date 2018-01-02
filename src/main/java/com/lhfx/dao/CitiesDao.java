package com.lhfx.dao;

import com.lhfx.entity.Cities;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;
import java.io.Serializable;

@Mapper
public interface CitiesDao extends Serializable{

    Cities getById(final Integer id);

    int deleteById(String[] ids);

    int flagBatchId(Map<String, Object> params);

    int updateByEntity(final Cities entity);

    int batchUpdateByEntity(List<Cities> entity);

    int insert(final Cities entity);

    int batchInsert(final List<Cities> list);

    List<Cities> list(final Map<String, Object> params);

    long count(final Map<String, Object> params);

    List<Map<String, String>> dict(Map<String, Object> params);


}