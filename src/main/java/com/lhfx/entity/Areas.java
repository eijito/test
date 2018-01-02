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

@ApiModel(value = "行政区域县区信息表")
public class Areas implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private Integer id;
    
	@ApiModelProperty(value = "")
    private String area;
	@ApiModelProperty(value = "")
    private String areaid;
	@ApiModelProperty(value = "")
    private String cityid;
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getAreaid() {
        return this.areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }
    public String getCityid() {
        return this.cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }
    
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}