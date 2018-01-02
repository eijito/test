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

@ApiModel(value = "需求")
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;

    
	@ApiModelProperty(value = "需求")
    private String request;
	@ApiModelProperty(value = "公司id")
    private Integer companyId;
    

    public String getRequest() {
        return this.request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}