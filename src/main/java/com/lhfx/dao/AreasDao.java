package com.lhfx.dao;

import com.lhfx.entity.Areas;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;
import java.io.Serializable;

@Mapper
public interface AreasDao extends Serializable{

    Areas getById(final Integer id);

    int deleteById(String[] ids);

    int flagBatchId(Map<String, Object> params);

    int updateByEntity(final Areas entity);

    int batchUpdateByEntity(List<Areas> entity);

    int insert(final Areas entity);

    int batchInsert(final List<Areas> list);

    List<Areas> list(final Map<String, Object> params);

    long count(final Map<String, Object> params);

    List<Map<String, String>> dict(Map<String, Object> params);


}