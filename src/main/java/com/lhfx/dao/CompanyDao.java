package com.lhfx.dao;

import com.lhfx.entity.Company;
import com.lhfx.entity.Request;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;
import java.io.Serializable;

@Mapper
public interface CompanyDao extends Serializable{

    Company getById(final Integer id);

    int deleteById(String[] ids);

    int flagBatchId(Map<String, Object> params);

    int updateByEntity(final Company entity);

    int batchUpdateByEntity(List<Company> entity);

    int insert(final Company entity);

    int batchInsert(final List<Company> list);

    List<Company> list(final Map<String, Object> params);

    long count(final Map<String, Object> params);

    List<Map<String, String>> dict(Map<String, Object> params);


    /** linked table [request] start */
    long linkedRequest(@Param("companyIds") String[] companyIds);
    long deleteLinkedRequest(@Param("companyIds") String... companyIds);

    int deleteBeyondRequest(@Param("companyId") Integer companyId, @Param("requests") List<Request> requests);
    int insertRequestIfMiss(@Param("companyId") Integer companyId, @Param("requests") List<Request> list);
    int updateRequestIfExist(List<Request> list);
    /** linked table [request] end */

}