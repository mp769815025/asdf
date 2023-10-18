package com.zhirong.liaohui.controller;

import com.zhirong.liaohui.service.UserService;
import com.zhirong.liaohui.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/try")
@EnableAutoConfiguration
public class UserController {




    @Autowired
    private UserService userService;


    @RequestMapping(value = "/getAll")
    public ResultVo getAllUsers(){
        return userService.getAllUsers();
    }





}