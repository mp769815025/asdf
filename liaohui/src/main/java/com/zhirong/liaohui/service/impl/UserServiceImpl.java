package com.zhirong.liaohui.service.impl;

import com.zhirong.liaohui.dao.UserDao;
import com.zhirong.liaohui.domain.User;
import com.zhirong.liaohui.service.UserService;
import com.zhirong.liaohui.vo.CodeMsg;
import com.zhirong.liaohui.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResultVo getAllUsers() {
        List<User> allUsers = userDao.getAllUsers();
        if (allUsers.size() == 0) {
            return ResultVo.error(CodeMsg.SELECT_ERROR);
        } else {
            return ResultVo.success(allUsers);
        }

    }
}