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

@ApiModel(value = "省份信息表")
public class Provinces implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private Integer id;
    
	@ApiModelProperty(value = "")
    private String province;
	@ApiModelProperty(value = "")
    private String provinceid;
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    public String getProvinceid() {
        return this.provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }
    
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}