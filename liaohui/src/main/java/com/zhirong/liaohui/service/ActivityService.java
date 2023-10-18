package com.zhirong.liaohui.service;

import com.zhirong.liaohui.entity.Activity;
import com.zhirong.liaohui.qo.ActivityRequest;
import com.zhirong.liaohui.qo.MyPageRequest;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * 活动(Activity)表服务接口
 *
 * @author makejava
 * @since 2023-10-13 10:29:41
 */
public interface ActivityService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Activity queryById(Integer id);

    /**
     * 分页查询
     *
     * @param activity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Activity> queryByPage(Activity activity, MyPageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param activity       实例对象
     * @param servletRequest
     * @return 实例对象
     */
    Activity insert(ActivityRequest activity, HttpServletRequest servletRequest);

    /**
     * 修改数据
     *
     * @param activity       实例对象
     * @param servletRequest
     * @return 实例对象
     */
    Activity update(ActivityRequest activity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
