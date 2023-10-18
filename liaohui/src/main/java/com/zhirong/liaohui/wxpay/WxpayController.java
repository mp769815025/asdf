package com.zhirong.liaohui.wxpay;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.gson.Gson;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.zhirong.liaohui.util.JWTUtils;
import com.zhirong.liaohui.vo.ResultVo;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags="微信支付")
public class WxpayController {
    private String REQUEST_URL="https://api.weixin.qq.com/sns/jscode2session";

    private String GRANT_TYPE="authorization_code";

    private static String APP_ID="wx922051acc2e89c24";

    private static String Mch_id="1655657824";

    private static String gzh_SECRET="2b765cc6e6604310782d211150e5710e";
    private static String xch_SECRET ="c14a95b13af68a69c428a48e5a0d5e71";

    private static String APIv3_KEY="Mp134399ksej834jjsu8jnskk348hjsd";

    private static String openId="oUHh469xXOJFYibv6kmCTdX8zEXQ";

    private static String MCH_SERIALNO="4F1A730BD64363D1E8B6009D4C648058587A3E96";
    private static String CERTIFICATE_PATH="D:\\miyao\\apiclient_key.pem";

    private static String  NOTIFY_URL="http://101.35.218.215/payCallBack";

    private static String linuxPath="/home/liaohui/miyao/apiclient_key.pem";


    public static void main(String[] args) throws Exception {
        queryOrder();
    }


    @GetMapping("/payPreview")
    public ResultVo<Map<String,String>> payPreview(HttpServletRequest httpRequest,String outTradeNo) throws Exception {
        // 加载商户私钥,私钥路径
        PrivateKey merchantPrivateKey = PemUtil
                .loadPrivateKey(new FileInputStream(linuxPath));

        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(Mch_id, new PrivateKeySigner(MCH_SERIALNO, merchantPrivateKey)),APIv3_KEY.getBytes("utf-8"));

        // 初始化httpClient
        HttpClient httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(Mch_id, MCH_SERIALNO, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier)).build();
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
        Gson gson = new Gson();
        JSONObject jsonObjects = new JSONObject();
        jsonObjects.put("mchid","1655657824");
        jsonObjects.put("out_trade_no",outTradeNo);
        jsonObjects.put("goods_tag","WXG");
        jsonObjects.put("appid","wx922051acc2e89c24");
        jsonObjects.put("description","Image形象店-深圳腾大-QQ公仔");
        jsonObjects.put("notify_url",NOTIFY_URL);
        JSONObject moneyJson = new JSONObject();
        moneyJson.put("total",10);
        moneyJson.put("currency","CNY");
        jsonObjects.put("amount",moneyJson);
        JSONObject payerJson = new JSONObject();
        String md5Token=httpRequest.getHeader("author_token");
        DecodedJWT verify = JWTUtils.getToken(md5Token);
        String openId=verify.getClaim("author_token").asString();
        System.out.println("openId"+openId);
        payerJson.put("openid",openId);
        jsonObjects.put("payer",payerJson);
        String jsonStrings = gson.toJson(jsonObjects);
        StringEntity entity = new StringEntity(jsonStrings,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity httpEntity=response.getEntity();
        InputStream inputStream=httpEntity.getContent();
        String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        JSONObject backJson=JSONObject.parseObject(result);
        String prepayId=(String)backJson.get("prepay_id");
        //这里如果返回不成功可能是HttpClient出了问题，后面注意下，然后做下单json返回
        Long timeStamp = System.currentTimeMillis() / 1000;
        String noceStr=WXPayUtil.generateNonceStr();
        Map<String,String> stringObjectMap = new HashMap<>();
        Map<String,String> signMap= new HashMap<>();
        stringObjectMap.put("nonceStr",noceStr);
        stringObjectMap.put("signType","RSA");
        stringObjectMap.put("timeStamp",String.valueOf(timeStamp));
        stringObjectMap.put("package","prepay_id="+prepayId);
        stringObjectMap.put("prepay_id",prepayId);
        signMap.put("appId",APP_ID);
        signMap.put("timeStamp",String.valueOf(timeStamp));
        signMap.put("nonceStr",noceStr);
        signMap.put("package","prepay_id="+prepayId);
        String signStr=buildMessage(APP_ID,String.valueOf(timeStamp),noceStr,"prepay_id="+prepayId);
        String signResult=sign(merchantPrivateKey,signStr.getBytes("utf-8"));
        stringObjectMap.put("paySign",signResult);
        stringObjectMap.put("appId",APP_ID);
        return ResultVo.success(stringObjectMap);
    }

    public static String sign(PrivateKey merchantPrivateKey,byte[] message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, FileNotFoundException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(merchantPrivateKey);
        sign.update(message);
        return java.util.Base64.getEncoder().encodeToString(sign.sign());
    }
    public static String buildMessage(String appId,String timestamp,String nonceStr,String repayId) {
        return appId + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + repayId + "\n";
    }


    @PostMapping("/payCallBack")
    public  void payCallBack(HttpServletRequest request){
        Map<String, String[]> maps=request.getParameterMap();
        String mapJson=JSONObject.toJSONString(maps);
        System.out.println("微信官方支付回调了"+mapJson);
    }



    @PostMapping("/payRefundBack")
    public  void payRefundBack(HttpServletRequest request){
        Map<String, String[]> maps=request.getParameterMap();
        String mapJson=JSONObject.toJSONString(maps);
        System.out.println("微信官方退款回调了"+mapJson);
    }

    public static  void queryOrder() throws Exception {
        PrivateKey merchantPrivateKey = PemUtil
                .loadPrivateKey(new FileInputStream(CERTIFICATE_PATH));

        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(Mch_id, new PrivateKeySigner(MCH_SERIALNO, merchantPrivateKey)),APIv3_KEY.getBytes("utf-8"));

        // 初始化httpClient
        HttpClient httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(Mch_id, MCH_SERIALNO, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier)).build();

        HttpGet httpGet = new HttpGet();
        URI.create("https://api.mch.weixin.qq.com/pay/refundquery");

        httpGet.setHeader("out_refund_no","68446456565659");
        httpClient.execute(httpGet);
        System.out.println();

    }

    public static String sendGet(HttpClient httpClient,String url, Map<String, String> header) {
        HttpGet httpGet = new HttpGet(url);


        //设置头部
        for(Map.Entry entry:header.entrySet()){
//            System.out.println(entry.getKey()+ "###########" + entry.getValue());
            httpGet.setHeader(entry.getKey().toString(),entry.getValue().toString());
        }
//        System.out.println(jsonObject.toString());


//        HttpGet httpget = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            System.out.println();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }


    @GetMapping("/payRefund")
    public  void payRefund(HttpRequest request) throws IOException {


        PrivateKey merchantPrivateKey = PemUtil
                .loadPrivateKey(new FileInputStream(linuxPath));

        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(Mch_id, new PrivateKeySigner(MCH_SERIALNO, merchantPrivateKey)),APIv3_KEY.getBytes("utf-8"));

        // 初始化httpClient
        HttpClient httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(Mch_id, MCH_SERIALNO, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier)).build();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out_trade_no", "68446456565659");
        jsonObject.put("out_refund_no", "68446456565610");
        jsonObject.put("notify_url","http://101.35.218.215/payRefundBack");
        JSONObject moneyJson = new JSONObject();
        moneyJson.put("refund",10);
        moneyJson.put("total",10);
        moneyJson.put("currency","CNY");
        jsonObject.put("amount",moneyJson);
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/refund/domestic/refunds");
        Gson gson = new Gson();
        String jsonStrings = gson.toJson(jsonObject);
        StringEntity entity = new StringEntity(jsonStrings,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpClient.execute(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity httpEntity=response.getEntity();
        InputStream inputStream=httpEntity.getContent();
        String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        System.out.println(result);
        System.out.println();
    }



    @GetMapping("/WxCallback")
    public ResponseEntity WxCallback(HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        String errcode = request.getParameter("errcode");
        String state = request.getParameter("state");

        return null;
    }

}
