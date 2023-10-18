package com.zhirong.liaohui.service;

import com.zhirong.liaohui.entity.WechatUser;
import com.zhirong.liaohui.qo.WechatLoginRequest;

/**
 * 小程序用户表(WechatUser)表服务接口
 *
 * @author makejava
 * @since 2023-10-09 15:27:50
 */
public interface WechatUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WechatUser queryById(Integer id);

    /**
     * 分页查询
     *
     * @param wechatUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    Page<WechatUser> queryByPage(WechatUser wechatUser, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param wechatUser 实例对象
     * @return 实例对象
     */
    WechatUser insert(WechatUser wechatUser);

    /**
     * 修改数据
     *
     * @param wechatUser 实例对象
     * @return 实例对象
     */
    WechatUser update(WechatUser wechatUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    String getUserInfoMap(WechatLoginRequest loginRequest);

}
