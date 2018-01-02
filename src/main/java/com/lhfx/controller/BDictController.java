package com.lhfx.controller;

import com.lhfx.service.DSkillService;
import com.youngo.passport.dict.DictionaryVo;
import com.youngo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "字典", description = "杂项维护")
@RequestMapping(value = "/dict")
@RestController
public class BDictController {

    @Autowired
    protected DSkillService skillService;

    @ApiOperation(value="技能字典",response = DictionaryVo.class)
    @RequestMapping(value = "/skill", method = RequestMethod.GET)
    public Result deviceDict() {
        return Result.formatRet(skillService.dict());
    }
    @ApiOperation(value="技能添加",response = DictionaryVo.class)
    @RequestMapping(value = "/skill",method = RequestMethod.POST,  produces = "application/json")
    public Result deviceCreate(@RequestParam("name") String name){
        return Result.formatRet(skillService.createValueName(name,null));
    }
    @ApiOperation(value="技能修改",response = DictionaryVo.class)
    @RequestMapping(value = "/skill/{id}", method = RequestMethod.PUT)
    public Result deviceModify(@PathVariable("id") int id,
                                  @RequestParam("name") String name) {
        return Result.formatRet(skillService.modifyByValue(name,id));
    }
    @ApiOperation(value="技能删除")
    @RequestMapping(value = "/skill/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deviceDelete(@PathVariable("id") int id){
        return Result.formatRet(skillService.delete(id));
    }
}
