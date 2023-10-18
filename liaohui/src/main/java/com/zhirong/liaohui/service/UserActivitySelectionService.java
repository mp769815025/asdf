package com.zhirong.liaohui.service;

import com.zhirong.liaohui.entity.UserActivitySelection;
import com.zhirong.liaohui.qo.MyPageRequest;
import org.springframework.data.domain.Page;

/**
 * 用户活动报名自定义选项(UserActivitySelection)表服务接口
 *
 * @author makejava
 * @since 2023-10-13 11:10:53
 */
public interface UserActivitySelectionService {

    /**
     * 通过ID查询单条数据
     *
     * @param activityId 主键
     * @return 实例对象
     */
    UserActivitySelection queryById(Integer activityId);

    /**
     * 分页查询
     *
     * @param userActivitySelection 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<UserActivitySelection> queryByPage(UserActivitySelection userActivitySelection, MyPageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param userActivitySelection 实例对象
     * @return 实例对象
     */
    UserActivitySelection insert(UserActivitySelection userActivitySelection);

    /**
     * 修改数据
     *
     * @param userActivitySelection 实例对象
     * @return 实例对象
     */
    UserActivitySelection update(UserActivitySelection userActivitySelection);

    /**
     * 通过主键删除数据
     *
     * @param activityId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer activityId);

}
