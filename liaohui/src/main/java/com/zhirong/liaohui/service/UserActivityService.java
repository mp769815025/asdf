package com.zhirong.liaohui.service;

import com.zhirong.liaohui.entity.UserActivity;
import com.zhirong.liaohui.qo.MyPageRequest;
import org.springframework.data.domain.Page;

/**
 * (UserActivity)表服务接口
 *
 * @author makejava
 * @since 2023-10-13 09:47:41
 */
public interface UserActivityService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserActivity queryById(Integer id);

    /**
     * 分页查询
     *
     * @param userActivity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<UserActivity> queryByPage(UserActivity userActivity, MyPageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param userActivity 实例对象
     * @return 实例对象
     */
    UserActivity insert(UserActivity userActivity);

    /**
     * 修改数据
     *
     * @param userActivity 实例对象
     * @return 实例对象
     */
    UserActivity update(UserActivity userActivity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
