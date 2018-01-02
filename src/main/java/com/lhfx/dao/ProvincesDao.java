package com.lhfx.dao;

import com.lhfx.entity.Provinces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;
import java.io.Serializable;

@Mapper
public interface ProvincesDao extends Serializable{

    Provinces getById(final Integer id);

    int deleteById(String[] ids);

    int flagBatchId(Map<String, Object> params);

    int updateByEntity(final Provinces entity);

    int batchUpdateByEntity(List<Provinces> entity);

    int insert(final Provinces entity);

    int batchInsert(final List<Provinces> list);

    List<Provinces> list(final Map<String, Object> params);

    long count(final Map<String, Object> params);

    List<Map<String, String>> dict(Map<String, Object> params);


}