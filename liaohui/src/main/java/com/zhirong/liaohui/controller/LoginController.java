package com.zhirong.liaohui.controller;


import com.zhirong.liaohui.qo.WechatLoginRequest;
import com.zhirong.liaohui.service.WechatUserService;
import com.zhirong.liaohui.vo.CodeMsg;
import com.zhirong.liaohui.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@EnableAutoConfiguration
public class LoginController {



    @Autowired
    private WechatUserService wechatUserService;

    @PostMapping("/login")
    public ResultVo<String> login(@RequestBody WechatLoginRequest wechatLoginRequest){
        if(StringUtils.isEmpty(wechatLoginRequest.getPhoneCode() )|| StringUtils.isEmpty(wechatLoginRequest.getCode())){
            CodeMsg codeMsg = new CodeMsg(100002,"code : "+wechatLoginRequest.getCode()+"phoneCode :  "+wechatLoginRequest.getPhoneCode());
            return ResultVo.error(codeMsg);
        }
        String token=wechatUserService.getUserInfoMap(wechatLoginRequest);//返回的openId已经加密，后续服务器通过解密拿到openId
        return ResultVo.success(token);
    }




}