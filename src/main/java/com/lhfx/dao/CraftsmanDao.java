package com.lhfx.dao;

import com.lhfx.entity.Craftsman;
import com.lhfx.entity.CraftsmanSkill;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;
import java.io.Serializable;

@Mapper
public interface CraftsmanDao extends Serializable{

    Craftsman getById(final Integer id);

    int deleteById(String[] ids);

    int flagBatchId(Map<String, Object> params);

    int updateByEntity(final Craftsman entity);

    int batchUpdateByEntity(List<Craftsman> entity);

    int insert(final Craftsman entity);

    int batchInsert(final List<Craftsman> list);

    List<Craftsman> list(final Map<String, Object> params);

    long count(final Map<String, Object> params);

    List<Map<String, String>> dict(Map<String, Object> params);


    /** linked table [craftsman_skill] start */
    long linkedCraftsmanSkill(@Param("craftsmanIds") String[] craftsmanIds);
    long deleteLinkedCraftsmanSkill(@Param("craftsmanIds") String... craftsmanIds);

    int deleteBeyondCraftsmanSkill(@Param("craftsmanId") Integer craftsmanId, @Param("craftsmanSkills") List<CraftsmanSkill> craftsmanSkills);
    int insertCraftsmanSkillIfMiss(@Param("craftsmanId") Integer craftsmanId, @Param("craftsmanSkills") List<CraftsmanSkill> list);
    int updateCraftsmanSkillIfExist(List<CraftsmanSkill> list);
    /** linked table [craftsman_skill] end */

}