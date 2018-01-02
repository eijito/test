package com.lhfx.controller;

import com.lhfx.abstra.WeixinBaseRestful;
import com.lhfx.abstra.WxService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "小天使公众号", description = "小天使公众号的相关授权操作")
@Configuration
@RestController
@RequestMapping("/angelWx")
public class AngelController extends WeixinBaseRestful {
    private static final String wx_prefix = "ANGEL_WX";
    private static final String wx_id = "wx0750af95405fcf4f";
    private static final String wx_secret = "0101ebff9eb8f0344e2aac8aae52fece";

    @Autowired
    @Qualifier("angel")
    public void setWxService(WxService wxService) {
        super.setWxService(wxService);
    }

    @Bean
    public WxService angel() {
        return new WxService(wx_id,wx_secret,wx_prefix);
    }
}
