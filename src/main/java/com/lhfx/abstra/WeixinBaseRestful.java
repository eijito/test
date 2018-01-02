package com.lhfx.abstra;

import com.youngo.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class WeixinBaseRestful {

    protected WxService wxService;

    public void setWxService(WxService wxService) {
        this.wxService = wxService;
    }

    @ApiOperation(value = "微信api签名")
    @RequestMapping(value = "/signature", method = RequestMethod.GET,produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "接口url",  paramType = "query",dataType="string")})
    public Map<String, Object> signature(HttpServletRequest request) {
        String url = request.getParameter("url");
        if (StringUtils.isBlank(url)) {
            url = request.getRequestURL().toString();
        }
        return wxService.getSignature(url);
    }

    @ApiOperation(value = "微信普通Token")
    @RequestMapping(value = "/accessToken", method = RequestMethod.GET)
    public String getAccessToken() {
        return wxService.getAccessToken();
    }

    @ApiOperation(value = "微信网页授权Token")
    @RequestMapping(value = "/wapAccessToken/{code}", method = RequestMethod.GET)
    public Result getWapAccessToken(@ApiParam(value = "授权code", required = true) @PathVariable("code") String code) {
        return wxService.getWapAccessToken(code);
    }

    public static void main(String[] args) {
//        changeToMp3("F:\\workspace\\YounGoERP\\homework_server\\abdc_1495588476457.amr","F:\\workspace\\YounGoERP\\homework_server\\abdc_1495588476457.mp3");
    }
}
