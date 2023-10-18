package com.zhirong.liaohui.service;

import com.zhirong.liaohui.vo.ResultVo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public ResultVo getAllUsers();

}