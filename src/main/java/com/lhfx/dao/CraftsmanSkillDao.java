package com.lhfx.dao;

import com.lhfx.entity.CraftsmanSkill;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;
import java.io.Serializable;

@Mapper
public interface CraftsmanSkillDao extends Serializable{


    int insert(final CraftsmanSkill entity);

    int batchInsert(final List<CraftsmanSkill> list);

    List<CraftsmanSkill> list(final Map<String, Object> params);

    long count(final Map<String, Object> params);

    List<Map<String, String>> dict(Map<String, Object> params);


}