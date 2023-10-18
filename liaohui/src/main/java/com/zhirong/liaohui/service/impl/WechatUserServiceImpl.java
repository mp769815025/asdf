package com.zhirong.liaohui.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhirong.liaohui.dao.WechatUserDao;
import com.zhirong.liaohui.entity.WechatUser;
import com.zhirong.liaohui.qo.WechatLoginRequest;
import com.zhirong.liaohui.service.WechatUserService;
import com.zhirong.liaohui.util.HttpClientUtils;
import com.zhirong.liaohui.util.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 小程序用户表(WechatUser)表服务实现类
 *
 * @author makejava
 * @since 2023-10-09 15:27:50
 */
@Service("wechatUserService")
public class WechatUserServiceImpl implements WechatUserService {
    @Resource
    private WechatUserDao wechatUserDao;

    private String REQUEST_URL="https://api.weixin.qq.com/sns/jscode2session";

    private String GRANT_TYPE="authorization_code";

    private String APP_ID="wx922051acc2e89c24";

    private String gzh_SECRET="2b765cc6e6604310782d211150e5710e";
    private String xch_SECRET ="c14a95b13af68a69c428a48e5a0d5e71";



    public String getUserInfoMap(WechatLoginRequest loginRequest)   {
        WechatUser insertOrUpdateDO = new WechatUser();
        String openId=null;
        String phoneNum=null;
        if(StringUtils.isNotEmpty(loginRequest.getCode())){
            JSONObject sessionKeyOpenId = getSessionKeyOrOpenId(loginRequest.getCode());
            // 获取openId && sessionKey
            openId = sessionKeyOpenId.getString("openid");
            String sessionKey = sessionKeyOpenId.getString("session_key");
            System.out.println("openId"+"=========>"+openId+"    "+"sessionkey"+"========>"+sessionKey);
            insertOrUpdateDO.setOpenId(openId);
        }
        WechatUser wechatUser=wechatUserDao.getUserByOpenId(openId);
        if(StringUtils.isNotEmpty(loginRequest.getPhoneCode())) {
            phoneNum = getPhoneNum(loginRequest.getPhoneCode());
            insertOrUpdateDO.setMobile(phoneNum);
        }
        if(Objects.isNull(wechatUser) ){
            wechatUserDao.insert(insertOrUpdateDO);
        }else {
            wechatUser.setOpenId(openId);
            wechatUser.setMobile(phoneNum);
            wechatUserDao.updateByOpenid(wechatUser);
        }
        Map<String, String> payload = new HashMap<>();
        payload.put("author_token",openId);
        String token=JWTUtils.getToken(payload);
        return token;
    }


    public static String checkAccessToken(String openid, String accessToken) {
        StringBuffer stringBuffer = new StringBuffer().append("https://api.weixin.qq.com/sns/auth")
                .append("?access_token=" + accessToken)
                .append("&openid=" + openid)
                .append("&lang=zh_CN");
        return stringBuffer.toString();
    }
    public  String getPhoneNum(String phoneCode) {
        String tokenUrl = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", APP_ID, xch_SECRET);
        JSONObject token = JSON.parseObject(HttpClientUtils.doGet(tokenUrl));
        String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + token.getString("access_token");

        //封装请求体
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("code", phoneCode);

        //封装请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(paramMap,headers);

        //通过RestTemplate发送请求，获取到用户手机号码
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.postForEntity(url, httpEntity, Object.class);
        Map map=(Map)response.getBody();
        Map maps= (Map)map.get("phone_info");
        String phoneNumber=(String)maps.get("phoneNumber");
        return phoneNumber;
    }

    //调用接口
    private JSONObject getSessionKeyOrOpenId(String code) {
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("appid", APP_ID);
        requestUrlParam.put("secret", xch_SECRET);
        requestUrlParam.put("js_code", code);
        requestUrlParam.put("grant_type", GRANT_TYPE);

        // 发送post请求读取调用微信接口获取openid用户唯一标识
        String result = HttpClientUtils.doPost(REQUEST_URL, requestUrlParam);
        return JSON.parseObject(result);
    }

//    private WechatUser buildWechatUserAuthInfoDO(WechatLoginRequest loginRequest, String sessionKey, String openId){
//        WechatUser wechatUserDO = new WechatUser();
//        wechatUserDO.setOpenId(openId);
//
//        if (loginRequest.getRawData() != null) {
//            RawDataDO rawDataDO = JSON.parseObject(loginRequest.getRawData(), RawDataDO.class);
//            wechatUserDO.setNickname(rawDataDO.getNickName());
//            wechatUserDO.setAvatarUrl(rawDataDO.getAvatarUrl());
//            wechatUserDO.setGender(rawDataDO.getGender());
//            wechatUserDO.setCity(rawDataDO.getCity());
//            wechatUserDO.setCountry(rawDataDO.getCountry());
//            wechatUserDO.setProvince(rawDataDO.getProvince());
//        }
//
//        // 解密加密信息，获取unionID
//        if (loginRequest.getEncryptedData() != null){
//            JSONObject encryptedData = getEncryptedData(loginRequest.getEncryptedData(), sessionKey, loginRequest.getIv());
//            if (encryptedData != null){
//                String unionId = encryptedData.getString("unionId");
//                wechatUserDO.setUnionId(unionId);
//            }
//        }
//
//        return wechatUserDO;
//    }

    private JSONObject getEncryptedData(String encryptedData, String sessionkey, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionkey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.这个if中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + 1;
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            System.out.println("解密加密信息报错");
            e.printStackTrace();
        }
        return null;
    }

//    生成token 这里可以忽略 根据自己当前业务系统选取生成方式
//    public ResponseResult createToken(WechatUser wechatUser) {
//        String openid = wechatUser.getOpenId();
//        String token = MD5Util.getMD5Str(openid + System.currentTimeMillis());
//
//        String flyingSessionId = MD5Util.getMD5Str("HAHA" + wechatUser.getOpenId());
//        wechatUser.getStringRedisTemplate().opsForValue().set(token, wechatUser.getNickname());
//
//        //外部登录生成token
//        String key = token + flyingSessionId;
//        Map<String, String> redisData = new HashMap<>();
//        redisData.put("HAHA-TOKEN", token);
//        redisData.put("HAHA-SESSIONID", flyingSessionId);
//        redisData.put("uid", wechatUser.getId() + "");
//        redisData.put("openid", wechatUser.getOpenId());
//        redisData.put("nickname", wechatUser.getNickname());
//        wechatUser.getStringRedisTemplate().opsForHash().putAll(key, redisData);
//        wechatUser.getStringRedisTemplate().expire(key, 86400, TimeUnit.SECONDS);
//        return ResponseResult.okResult(redisData);
//    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WechatUser queryById(Integer id) {
        return this.wechatUserDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param wechatUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @Override
//    public Page<WechatUser> queryByPage(WechatUser wechatUser, PageRequest pageRequest) {
//        long total = this.wechatUserDao.count(wechatUser);
//        return new PageImpl<>(this.wechatUserDao.queryAllByLimit(wechatUser, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param wechatUser 实例对象
     * @return 实例对象
     */
    @Override
    public WechatUser insert(WechatUser wechatUser) {
        this.wechatUserDao.insert(wechatUser);
        return wechatUser;
    }

    /**
     * 修改数据
     *
     * @param wechatUser 实例对象
     * @return 实例对象
     */
    @Override
    public WechatUser update(WechatUser wechatUser) {
        this.wechatUserDao.updateByOpenid(wechatUser);
        return this.queryById(wechatUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.wechatUserDao.deleteById(id) > 0;
    }



}
