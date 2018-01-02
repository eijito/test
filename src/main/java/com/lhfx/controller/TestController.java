package com.lhfx.controller;


import com.lhfx.dao.CitiesDao;
import com.lhfx.entity.Cities;
import com.youngo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "测试的controller" ,description = "测试的controller")
@RequestMapping(value = "test")
@RestController
public class TestController {

    @Autowired
    private CitiesDao citiesDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "测试test,显示所有的城市信息",response = Cities.class)
    public Result  test01(){
        List<Cities> list = citiesDao.list(null);
        return  Result.formatRet(list);
    }


}
