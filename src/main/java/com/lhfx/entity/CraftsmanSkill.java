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

@ApiModel(value = "工匠技能")
public class CraftsmanSkill implements Serializable {

	private static final long serialVersionUID = 1L;

    
	@ApiModelProperty(value = "craftsman_id")
    private Integer craftsmanId;
	@ApiModelProperty(value = "技能")
    private String skillName;
    

    public Integer getCraftsmanId() {
        return this.craftsmanId;
    }

    public void setCraftsmanId(Integer craftsmanId) {
        this.craftsmanId = craftsmanId;
    }
    public String getSkillName() {
        return this.skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}