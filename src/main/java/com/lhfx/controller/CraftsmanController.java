package com.lhfx.controller;

import com.youngo.utils.*;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import com.lhfx.entity.Craftsman;
import com.lhfx.service.CraftsmanService;

@Api(value = "工匠", description = "工匠相关api")
@RestController
@RequestMapping(value = "craftsman")
public class CraftsmanController extends YoungoController {

	private static final Logger logger=Logger.getLogger(CraftsmanController.class);
	
	@Autowired
	private CraftsmanService craftsmanService;

	@ApiOperation(value="列表查询",response = Craftsman.class)
    @ApiImplicitParams({            @ApiImplicitParam(name = "areaName", value = "区县",  paramType = "query",dataType="String") ,
            @ApiImplicitParam(name = "uid", value = "uid",  paramType = "query",dataType="int") ,
            @ApiImplicitParam(name = "cityName", value = "城市",  paramType = "query",dataType="String") ,
            @ApiImplicitParam(name = "provinceId", value = "省id",  paramType = "query",dataType="int") ,
            @ApiImplicitParam(name = "name", value = "呢称",  paramType = "query",dataType="String") ,
            @ApiImplicitParam(name = "mobile", value = "电话",  paramType = "query",dataType="int") ,
            @ApiImplicitParam(name = "areaId", value = "区县id",  paramType = "query",dataType="int") ,
            @ApiImplicitParam(name = "provinceName", value = "省",  paramType = "query",dataType="String") ,
            @ApiImplicitParam(name = "cityId", value = "城市id",  paramType = "query",dataType="int") 
            })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result query(HttpServletRequest request, @RequestParam int page, @RequestParam int pageSize){
        Map<String, Object> params = parseParam(request ,"areaName" ,"uid" ,"cityName" ,"provinceId" ,"name" ,"mobile" ,"areaId" ,"provinceName" ,"cityId");
        //param2Like(params,"name");
        try {
            return Result.formatRet(craftsmanService.page(params,page,pageSize));
        } catch (Exception e) {
            return Result.formatBussinessError(e.getMessage());
        }
    }

    @ApiOperation(value="列表查询导出",response = Craftsman.class)
	@ApiImplicitParams({                @ApiImplicitParam(name = "areaName", value = "区县",  paramType = "query",dataType="String") ,
                @ApiImplicitParam(name = "uid", value = "uid",  paramType = "query",dataType="int") ,
                @ApiImplicitParam(name = "cityName", value = "城市",  paramType = "query",dataType="String") ,
                @ApiImplicitParam(name = "provinceId", value = "省id",  paramType = "query",dataType="int") ,
                @ApiImplicitParam(name = "name", value = "呢称",  paramType = "query",dataType="String") ,
                @ApiImplicitParam(name = "mobile", value = "电话",  paramType = "query",dataType="int") ,
                @ApiImplicitParam(name = "areaId", value = "区县id",  paramType = "query",dataType="int") ,
                @ApiImplicitParam(name = "provinceName", value = "省",  paramType = "query",dataType="String") ,
                @ApiImplicitParam(name = "cityId", value = "城市id",  paramType = "query",dataType="int") ,
                @ApiImplicitParam(name = "selCols", value = "选择导出列(以，号连接)",  paramType = "query",dataType="String")
                })
	@RequestMapping(value="/excellExport",method=RequestMethod.GET)
	public void excellExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = parseParam(request ,"areaName" ,"uid" ,"cityName" ,"provinceId" ,"name" ,"mobile" ,"areaId" ,"provinceName" ,"cityId");
        //param2Like(params,"name");

		List<Craftsman> datas=craftsmanService.list(params);
		String sheetName="工匠列表";
		ExcelUtils2 excelMeta = ExcelUtils2.instant()
		        .meta("areaName","区县")
		        .meta("uid","uid")
		        .meta("cityName","城市")
		        .meta("provinceId","省id")
		        .meta("name","呢称")
		        .meta("mobile","电话")
		        .meta("areaId","区县id")
		        .meta("provinceName","省")
		        .meta("cityId","城市id");

        HSSFWorkbook hssfWorkbook = excelMeta.selectFileds(request.getParameter("selCols")).createWorkBook(datas,sheetName);
		ExcelUtils2.downWorkBook(response,sheetName,hssfWorkbook);

	}

    @ApiOperation(value="新增",response = Craftsman.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result add(HttpServletRequest request,
					  @ApiParam(value = "工匠明细", required = true) @RequestBody Craftsman bean){
		try {
			int ret = craftsmanService.add(bean);
			return Result.formatRet(ret);
		} catch (Exception e) {
			logger.error(e);
			return Result.formatBussinessError(e.getMessage());
		}
	}

	/**
	@ApiOperation(value="工匠字典")
	@ApiImplicitParams({        @ApiImplicitParam(name = "areaName", value = "区县",  paramType = "query",dataType="String") ,
        @ApiImplicitParam(name = "uid", value = "uid",  paramType = "query",dataType="int") ,
        @ApiImplicitParam(name = "cityName", value = "城市",  paramType = "query",dataType="String") ,
        @ApiImplicitParam(name = "provinceId", value = "省id",  paramType = "query",dataType="int") ,
        @ApiImplicitParam(name = "name", value = "呢称",  paramType = "query",dataType="String") ,
        @ApiImplicitParam(name = "mobile", value = "电话",  paramType = "query",dataType="int") ,
        @ApiImplicitParam(name = "areaId", value = "区县id",  paramType = "query",dataType="int") ,
        @ApiImplicitParam(name = "provinceName", value = "省",  paramType = "query",dataType="String") ,
        @ApiImplicitParam(name = "cityId", value = "城市id",  paramType = "query",dataType="int") 
        })
    @RequestMapping(value = "/dict", method = RequestMethod.GET)
    public Result dict(HttpServletRequest request) {
        Map<String, Object> params = parseParam(request ,"areaName" ,"uid" ,"cityName" ,"provinceId" ,"name" ,"mobile" ,"areaId" ,"provinceName" ,"cityId");
        return Result.formatRet(craftsmanService.dict(params));
    }
    */

	@ApiOperation(value="详细",response = Craftsman.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result detail(@ApiParam(value = "工匠id", required = true) @PathVariable("id") Integer id) {
        return Result.formatRet(craftsmanService.get(id));
    }

    @ApiOperation(value="更新",response = Craftsman.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Result update(@ApiParam(value = "工匠id", required = true) @PathVariable("id") Integer id,
						 @ApiParam(value = "工匠明细", required = true) @RequestBody Craftsman bean){
		bean.setId(id);
		try {
			return Result.formatRet(craftsmanService.update(bean));
		} catch (Exception e) {
			logger.error(e);
			return Result.formatBussinessError(e.getMessage());
		}
	}

/**
    @ApiOperation(value="审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "audit", value = "0,重置，1通过，2拒绝",  paramType = "query",dataType="String") ,
            @ApiImplicitParam(name = "auditMsg", value = "拒绝理由",  paramType = "query",dataType="String")
    })
    @RequestMapping(value = "/audit", method = RequestMethod.PUT)
    public Result audit(HttpServletRequest request,
                              @ApiParam(value = "工匠id(多个以，号连接)", required = true) @RequestParam String ids){
        Map<String, Object> params = parseParam(request ,"audit" ,"auditMsg");

        SecurityUser user = SecurityUtil.getUser();
        if (null == user){
            return Result.formatBussinessError(401,"请登陆");
        }
        params.put("auditManId",user.getUid());
        params.put("auditMan",user.getNickname());
        params.put("ids",ids);
        try {
            return Result.formatRet(craftsmanService.flagById(params));
        } catch (Exception e) {
            logger.error(e);
            return Result.formatBussinessError(e.getMessage());
        }
    }

    @ApiOperation(value="生/失效")
    @RequestMapping(value = "/enable", method = RequestMethod.PUT)
    public Result enable(
                         @ApiParam(value = "1，生效；0，失效", required = true) @RequestParam String flag,
                         @ApiParam(value = "主键id(多个以，号连接)", required = true) @RequestParam String ids){
        try {
            return Result.formatRet(craftsmanService.enable(flag,ids.split(",")));
        } catch (Exception e) {
            logger.error(e);
            return Result.formatBussinessError(e.getMessage());
        }
    }
    */

	@ApiOperation(value="删除")
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public Result batchDelete(@ApiParam(value = "工匠(多个以，号连接)", required = true) @RequestParam("ids") String ids){

        String[] idArr = ids.split(",");
        try {
            int ret = craftsmanService.delete(idArr);
            return Result.formatRet(ret);
        } catch (Exception e) {
            logger.error(e);
            return Result.formatBussinessError(e.getMessage());
        }
    }

}