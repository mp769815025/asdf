package com.zhirong.liaohui.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Login {
    private static  String appid="wx922051acc2e89c24";
    private static String secret="1628e47d1970d79820c348b4d7a21736";

    public static void main(String[] args) {
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential" +
                "&appid=" + appid +
                "&secret=" + secret;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(accessTokenUrl,String.class);
        String accessToken = null;
        if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK){
            String strBody = responseEntity.getBody();
            System.out.println();
//            JSONObject jsonObject = JSONObject.parseObject(strBody);
//            accessToken = jsonObject.getString("access_token");
//            log.info("accessToken:{}",accessToken);
        }
    }



}
