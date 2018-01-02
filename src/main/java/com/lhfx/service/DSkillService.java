package com.lhfx.service;

import com.youngo.passport.dict.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DSkillService extends DictService {

    @Autowired
    @Qualifier("jdbcTemplate")
    public void setJdbcDict(JdbcTemplate jdbcTemplate) {
        super.initJdbcDict(jdbcTemplate);
    }

    public DSkillService() {
        super("skill",DictKey.value);
    }



}
