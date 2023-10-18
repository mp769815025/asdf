package com.zhirong.liaohui.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Test {
    private static String redirectUrl="http://39677c13r7.zicp.vip:40917/liaohui/WxCallback";

    private static String appid="wx922051acc2e89c24";

    public static void main(String[] args) throws UnsupportedEncodingException {
        String targ="138%112%99%131%79%81%84%147%115%106%101%97%116%132%125%145%81%134%136%94%111%127%115%83%149%96%115%108%";
        String value=Md5Util.decrypt(targ);
        System.out.println();
//        String url=getUserUathUrl(appid,redirectUrl);
//        System.out.println(url);
    }

    public static String getUserUathUrl(String appid, String redirectUrl) throws UnsupportedEncodingException {
        StringBuffer getcodeUrl = new StringBuffer()
                .append("https://open.weixin.qq.com/connect/oauth2/authorize")
                .append("?appid=" + appid)
                .append("&redirect_uri=" + URLEncoder.encode(redirectUrl, "utf-8"))
                .append("&response_type=code")
                .append("&scope=snsapi_userinfo")
                .append("&state=" + System.currentTimeMillis())
                .append("#wechat_redirect");

        return getcodeUrl.toString();
    }
}
