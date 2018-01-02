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

import com.lhfx.entity.CraftsmanSkill;
import com.lhfx.service.CraftsmanSkillService;

@Api(value = "工匠技能", description = "工匠技能相关api")
@RestController
@RequestMapping(value = "craftsmanSkill")
public class CraftsmanSkillController extends YoungoController {

	private static final Logger logger=Logger.getLogger(CraftsmanSkillController.class);
	
	@Autowired
	private CraftsmanSkillService craftsmanSkillService;

	@ApiOperation(value="列表查询",response = CraftsmanSkill.class)
    @ApiImplicitParams({            @ApiImplicitParam(name = "craftsmanId", value = "craftsman_id",  paramType = "query",dataType="int") ,
            @ApiImplicitParam(name = "skillName", value = "技能",  paramType = "query",dataType="String") 
            })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result query(HttpServletRequest request, @RequestParam int page, @RequestParam int pageSize){
        Map<String, Object> params = parseParam(request ,"craftsmanId" ,"skillName");
        //param2Like(params,"name");
        try {
            return Result.formatRet(craftsmanSkillService.page(params,page,pageSize));
        } catch (Exception e) {
            return Result.formatBussinessError(e.getMessage());
        }
    }

    @ApiOperation(value="列表查询导出",response = CraftsmanSkill.class)
	@ApiImplicitParams({                @ApiImplicitParam(name = "craftsmanId", value = "craftsman_id",  paramType = "query",dataType="int") ,
                @ApiImplicitParam(name = "skillName", value = "技能",  paramType = "query",dataType="String") ,
                @ApiImplicitParam(name = "selCols", value = "选择导出列(以，号连接)",  paramType = "query",dataType="String")
                })
	@RequestMapping(value="/excellExport",method=RequestMethod.GET)
	public void excellExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = parseParam(request ,"craftsmanId" ,"skillName");
        //param2Like(params,"name");

		List<CraftsmanSkill> datas=craftsmanSkillService.list(params);
		String sheetName="工匠技能列表";
		ExcelUtils2 excelMeta = ExcelUtils2.instant()
		        .meta("craftsmanId","craftsman_id")
		        .meta("skillName","技能");

        HSSFWorkbook hssfWorkbook = excelMeta.selectFileds(request.getParameter("selCols")).createWorkBook(datas,sheetName);
		ExcelUtils2.downWorkBook(response,sheetName,hssfWorkbook);

	}

    @ApiOperation(value="新增",response = CraftsmanSkill.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Result add(HttpServletRequest request,
					  @ApiParam(value = "工匠技能明细", required = true) @RequestBody CraftsmanSkill bean){
		try {
			int ret = craftsmanSkillService.add(bean);
			return Result.formatRet(ret);
		} catch (Exception e) {
			logger.error(e);
			return Result.formatBussinessError(e.getMessage());
		}
	}

	/**
	@ApiOperation(value="工匠技能字典")
	@ApiImplicitParams({        @ApiImplicitParam(name = "craftsmanId", value = "craftsman_id",  paramType = "query",dataType="int") ,
        @ApiImplicitParam(name = "skillName", value = "技能",  paramType = "query",dataType="String") 
        })
    @RequestMapping(value = "/dict", method = RequestMethod.GET)
    public Result dict(HttpServletRequest request) {
        Map<String, Object> params = parseParam(request ,"craftsmanId" ,"skillName");
        return Result.formatRet(craftsmanSkillService.dict(params));
    }
    */


}