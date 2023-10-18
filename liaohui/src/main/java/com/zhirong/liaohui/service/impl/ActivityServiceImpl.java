package com.zhirong.liaohui.service.impl;

import com.zhirong.liaohui.dao.ActivityDao;
import com.zhirong.liaohui.entity.Activity;
import com.zhirong.liaohui.entity.UserActivity;
import com.zhirong.liaohui.entity.UserActivitySelection;
import com.zhirong.liaohui.qo.ActivityRequest;
import com.zhirong.liaohui.qo.MyPageRequest;
import com.zhirong.liaohui.service.ActivityService;
import com.zhirong.liaohui.service.UserActivitySelectionService;
import com.zhirong.liaohui.service.UserActivityService;
import com.zhirong.liaohui.util.Md5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 活动(Activity)表服务实现类
 *
 * @author makejava
 * @since 2023-10-13 10:29:41
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityDao activityDao;

    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private UserActivitySelectionService userActivitySelectionService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Activity queryById(Integer id) {
        return this.activityDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param activity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Activity> queryByPage(Activity activity, MyPageRequest pageRequests) {
        PageRequest pageRequestsDao =new PageRequest(pageRequests.getPage() - 1,pageRequests.getSize());
        long total = this.activityDao.count(activity);
        return new PageImpl<>(this.activityDao.queryAllByLimit(activity, pageRequestsDao), pageRequestsDao, total);
    }

    /**
     * 新增数据
     *
     * @param request        实例对象
     * @param servletRequest
     * @return 实例对象
     */
    @Override
    public Activity insert(ActivityRequest request, HttpServletRequest servletRequest) {
        Activity activity= new Activity();
        BeanUtils.copyProperties(request,activity);
        this.activityDao.insert(activity);
        //同时新增用户活动表
        UserActivity userActivity = new UserActivity();
        userActivity.setActivityId(activity.getId());
        userActivity.setType(1);
        String openIdMd5=servletRequest.getHeader("author_token");
        userActivity.setOpenId(Md5Util.encrypt(openIdMd5));
        userActivity.setAuditStatus(0);
        userActivityService.insert(userActivity);
        //同时新增活动自定义表
        UserActivitySelection userActivitySelection=request.getActivitySelection();
        userActivitySelection.setActivityId(activity.getId());
        userActivitySelectionService.insert(userActivitySelection);
        return activity;
    }

    /**
     * 修改数据
     *
     * @param request        实例对象
     * @param servletRequest
     * @return 实例对象
     */
    @Override
    public Activity update(ActivityRequest request ) {
        Activity activity= new Activity();
        BeanUtils.copyProperties(request,activity);
        this.activityDao.update(activity);
        userActivitySelectionService.update(request.getActivitySelection());
        Activity activitys=this.queryById(activity.getId());
        return activitys;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.activityDao.deleteById(id) > 0;
    }
}
