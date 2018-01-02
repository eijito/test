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

import com.lhfx.entity.Areas;
import com.lhfx.service.AreasService;

@Api(value = "行政区域县区信息表", description = "行政区域县区信息表相关api")
@RestController
@RequestMapping(value = "areas")
public class AreasController extends YoungoController {

	private static final Logger logger=Logger.getLogger(AreasController.class);
	
	@Autowired
	private AreasService areasService;

	@ApiOperation(value="列表查询",response = Areas.class)
    @ApiImplicitParams({            @ApiImplicitParam(name = "area", value = "",  paramType = "query",dataType="String") ,
            @ApiImplicitParam(name = "areaid", value = "",  paramType = "query",dataType="String") ,
            @ApiImplicitParam(name = "cityid", value = "",  paramType = "query",dataType="String") 
            })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result query(HttpServletRequest request, @RequestParam int page, @RequestParam int pageSize){
        Map<String, Object> params = parseParam(request ,"area" ,"areaid" ,"cityid");
        //param2Like(params,"name");
        try {
            return Result.formatRet(areasService.page(params,page,pageSize));
        } catch (Exception e) {
            return Result.formatBussinessError(e.getMessage());
        }
    }

    @ApiOperation(value="列表查询导出",response = Areas.class)
	@ApiImplicitParams({                @ApiImplicitParam(name = "area", value = "",  paramType = "query",dataType="String") ,
                @ApiImplicitParam(name = "areaid", value = "",  paramType = "query",dataType="String") ,
                @ApiImplicitParam(name = "cityid", value = "",  paramType = "query",dataType="String") ,
                @ApiImplicitParam(name = "selCols", value = "选择导出列(以，号连接)",  paramType = "query",dataType="String")
                })
	@RequestMapping(value="/excellExport",method=RequestMethod.GET)
	public void excellExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = parseParam(request ,"area" ,"areaid" ,"cityid");
        //param2Like(params,"name");

		List<Areas> datas=areasService.list(params);
		String sheetName="行政区域县区信息表列表";
		ExcelUtils2 excelMeta = ExcelUtils2.instant()
		        .meta("area","")
		        .meta("areaid","")
		        .meta("cityid","");

        HSSFWorkbook hssfWorkbook = excelMeta.selectFileds(request.getParameter("selCols")).createWorkBook(datas,sheetName);
		ExcelUtils2.downWorkBook(response,sheetName,hssfWorkbook);

	}

    @ApiOperation(value="新增",response = Areas.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result add(HttpServletRequest request,
					  @ApiParam(value = "行政区域县区信息表明细", required = true) @RequestBody Areas bean){
		try {
			int ret = areasService.add(bean);
			return Result.formatRet(ret);
		} catch (Exception e) {
			logger.error(e);
			return Result.formatBussinessError(e.getMessage());
		}
	}

	/**
	@ApiOperation(value="行政区域县区信息表字典")
	@ApiImplicitParams({        @ApiImplicitParam(name = "area", value = "",  paramType = "query",dataType="String") ,
        @ApiImplicitParam(name = "areaid", value = "",  paramType = "query",dataType="String") ,
        @ApiImplicitParam(name = "cityid", value = "",  paramType = "query",dataType="String") 
        })
    @RequestMapping(value = "/dict", method = RequestMethod.GET)
    public Result dict(HttpServletRequest request) {
        Map<String, Object> params = parseParam(request ,"area" ,"areaid" ,"cityid");
        return Result.formatRet(areasService.dict(params));
    }
    */

	@ApiOperation(value="详细",response = Areas.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result detail(@ApiParam(value = "行政区域县区信息表id", required = true) @PathVariable("id") Integer id) {
        return Result.formatRet(areasService.get(id));
    }

    @ApiOperation(value="更新",response = Areas.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Result update(@ApiParam(value = "行政区域县区信息表id", required = true) @PathVariable("id") Integer id,
						 @ApiParam(value = "行政区域县区信息表明细", required = true) @RequestBody Areas bean){
		bean.setId(id);
		try {
			return Result.formatRet(areasService.update(bean));
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
                              @ApiParam(value = "行政区域县区信息表id(多个以，号连接)", required = true) @RequestParam String ids){
        Map<String, Object> params = parseParam(request ,"audit" ,"auditMsg");

        SecurityUser user = SecurityUtil.getUser();
        if (null == user){
            return Result.formatBussinessError(401,"请登陆");
        }
        params.put("auditManId",user.getUid());
        params.put("auditMan",user.getNickname());
        params.put("ids",ids);
        try {
            return Result.formatRet(areasService.flagById(params));
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
            return Result.formatRet(areasService.enable(flag,ids.split(",")));
        } catch (Exception e) {
            logger.error(e);
            return Result.formatBussinessError(e.getMessage());
        }
    }
    */

	@ApiOperation(value="删除")
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public Result batchDelete(@ApiParam(value = "行政区域县区信息表(多个以，号连接)", required = true) @RequestParam("ids") String ids){

        String[] idArr = ids.split(",");
        try {
            int ret = areasService.delete(idArr);
            return Result.formatRet(ret);
        } catch (Exception e) {
            logger.error(e);
            return Result.formatBussinessError(e.getMessage());
        }
    }

}