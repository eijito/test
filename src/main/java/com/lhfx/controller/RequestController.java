package com.lhfx.controller;

import com.youngo.utils.*;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import com.lhfx.entity.Request;
import com.lhfx.service.RequestService;

@Api(value = "装修需求", description = "装修需求相关api")
@RestController
@RequestMapping(value = "request")
public class RequestController extends YoungoController {

	private static final Logger logger=Logger.getLogger(RequestController.class);
	
	@Autowired
	private RequestService requestService;

	@ApiOperation(value="列表查询",response = Request.class)
    @ApiImplicitParams({            @ApiImplicitParam(name = "request", value = "需求",  paramType = "query",dataType="String") ,
            @ApiImplicitParam(name = "companyId", value = "公司id",  paramType = "query",dataType="int") 
            })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result query(HttpServletRequest request, @RequestParam int page, @RequestParam int pageSize){
        Map<String, Object> params = parseParam(request ,"request" ,"companyId");
        //param2Like(params,"name");
        try {
            return Result.formatRet(requestService.page(params,page,pageSize));
        } catch (Exception e) {
            return Result.formatBussinessError(e.getMessage());
        }
    }

    @ApiOperation(value="新增",response = Request.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result add(HttpServletRequest request,
					  @ApiParam(value = "需求明细", required = true) @RequestBody Request bean){
		try {
			int ret = requestService.add(bean);
			return Result.formatRet(ret);
		} catch (Exception e) {
			logger.error(e);
			return Result.formatBussinessError(e.getMessage());
		}
	}

	/**
	@ApiOperation(value="需求字典")
	@ApiImplicitParams({        @ApiImplicitParam(name = "request", value = "需求",  paramType = "query",dataType="String") ,
        @ApiImplicitParam(name = "companyId", value = "公司id",  paramType = "query",dataType="int") 
        })
    @RequestMapping(value = "/dict", method = RequestMethod.GET)
    public Result dict(HttpServletRequest request) {
        Map<String, Object> params = parseParam(request ,"request" ,"companyId");
        return Result.formatRet(requestService.dict(params));
    }
    */


}