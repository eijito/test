package com.lhfx.abstra;

import com.alibaba.fastjson.JSONObject;
import com.youngo.utils.Result;
import com.youngo.utils.WebHttp;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信授权
 * youngo.crm.config.wx.notifyUrl=http\://work.e-youngo.com/crm/wxpaynotify/async
 youngo.crm.config.wx.depositnotifyUrl=http\://work.e-youngo.com/crm/wxpaynotify/depositnotify
 youngo.crm.config.wx.appId=wx1d7b54006c491645
 youngo.crm.config.wx.key=liuhongpingyoungo2017shangshicgl
 youngo.crm.config.wx.mchID=1292757201
 youngo.crm.config.wx.secret=e3bfa105eee44f29702384cfbe47a8a8
 */
public class WxService {
    private Logger logger = Logger.getLogger(WxService.class);

    private String app_id = "wx1d7b54006c491645";
    private String app_secret = "e3bfa105eee44f29702384cfbe47a8a8";
    private String access_token_key = "YOUNGO_WX:ACCESS_TOKEN";
    private String ticket_key = "YOUNGO_WX:TICKET";

    public WxService(String app_id, String app_secret, String app_prefix){
        this.app_id = app_id;
        this.app_secret = app_secret;
        this.access_token_key = app_prefix + "_WX:ACCESS_TOKEN";
        this.ticket_key = app_prefix + "_WX:TICKET";
    }

    //普通token
    public static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    //api授权
    public static String js_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

    //网页授权
    public static String access_token_wap = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    //临时缓存授权
    public static String voice_load_url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";

//    @Autowired
//    private RedisService redisService;

    public File loadVoice(String mediaId) {
        File tmpFile = WebHttp.downLoad(String.format(voice_load_url, getAccessToken(), mediaId));
        return tmpFile;
    }

    public String getAccessToken() {
//        if (redisService.exists(access_token_key)) {
//            return redisService.get(access_token_key).toString();
//        }
        JSONObject result = WebHttp.doGet(String.format(access_token_url, app_id, app_secret));
        if (result.containsKey("access_token")) {
//            redisService.set(access_token_key, result.getString("access_token"), result.getIntValue("expires_in") - 10);
            return result.getString("access_token");
        }
        return "";
    }

    public Result getWapAccessToken(String code) {
        JSONObject ret = WebHttp.doGet(String.format(access_token_wap, app_id, app_secret,code));
        if (ret.containsKey("access_token")) {//取openid
            return Result.formatRet(ret.getString("openid"));
        } else {
            return Result.formatBussinessError(ret.getIntValue("errcode"),ret.getString("errmsg"));
        }
    }

    public String getJsTicket() {
//        if (redisService.exists(ticket_key)) {
//            return redisService.get(ticket_key).toString();
//        }
        String access_token = getAccessToken();
        if (StringUtils.isBlank(access_token)) return "";
        JSONObject result = WebHttp.doGet(String.format(js_ticket_url, access_token));
        if (result.containsKey("errmsg") && "ok".equals(result.getString("errmsg"))) {
//            redisService.set(ticket_key, result.getString("ticket"), result.getIntValue("expires_in"));
            return result.getString("ticket");
        }
        return "";
    }

    public Map<String, Object> getSignature(String url) {
        Map<String, Object> data = new HashMap<>();

        try {

            String nonce_str = "abcdefghijk";
            String timestamp = String.valueOf(getTimeStamp());
            String jsapi_ticket = getJsTicket();

            StringBuilder sb = new StringBuilder();
            sb.append("jsapi_ticket=").append(jsapi_ticket).append("&")
                    .append("noncestr=").append(nonce_str).append("&")
                    .append("timestamp=").append(timestamp).append("&")
                    .append("url=").append(url.indexOf("#") >= 0 ? url.substring(0, url.indexOf("#")) : url);

            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(sb.toString().getBytes("UTF-8"));
            String signature = byteToHex(crypt.digest());

            data.put("url", url);
            data.put("jsapi_ticket", jsapi_ticket);
            data.put("nonceStr", nonce_str);
            data.put("timestamp", timestamp);
            data.put("signature", signature);
            data.put("appId", app_id);

        } catch (Exception e) {
            logger.error(e.getStackTrace() + " " + e.getMessage(), e);
        }
        return data;
    }

    private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private long getTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    private long getTimeStamp(int seconds) {
        Calendar calendar = Calendar.getInstance();//获得当前时间
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTimeInMillis() / 1000;
    }
}
