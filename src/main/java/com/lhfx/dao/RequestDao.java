package com.lhfx.dao;

import com.lhfx.entity.Request;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;
import java.io.Serializable;

@Mapper
public interface RequestDao extends Serializable{


    int insert(final Request entity);

    int batchInsert(final List<Request> list);

    List<Request> list(final Map<String, Object> params);

    long count(final Map<String, Object> params);

    List<Map<String, String>> dict(Map<String, Object> params);


}