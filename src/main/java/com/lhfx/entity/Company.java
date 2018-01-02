package com.lhfx.entity;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.youngo.utils.DateFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

@ApiModel(value = "装饰公司")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;
    
	@ApiModelProperty(value = "区县")
    private String areaName;
	@ApiModelProperty(value = "uid")
    private Integer uid;
	@ApiModelProperty(value = "城市")
    private String cityName;
	@ApiModelProperty(value = "省id")
    private Integer provinceId;
	@ApiModelProperty(value = "呢称")
    private String name;
	@ApiModelProperty(value = "电话")
    private Integer mobile;
	@ApiModelProperty(value = "区县id")
    private Integer areaId;
	@ApiModelProperty(value = "省")
    private String provinceName;
	@ApiModelProperty(value = "城市id")
    private Integer cityId;

    @ApiModelProperty(value = "需求")
    private List<Request> requestList=new ArrayList<Request>(0);
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public Integer getProvinceId() {
        return this.provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getMobile() {
        return this.mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }
    public Integer getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
    public String getProvinceName() {
        return this.provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public Integer getCityId() {
        return this.cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    
	    public List<Request> getRequestList() {
        return this.requestList;
    }
    
    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}