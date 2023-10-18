package com.zhirong.liaohui.service.impl;

import com.zhirong.liaohui.dao.UserActivitySelectionDao;
import com.zhirong.liaohui.entity.UserActivitySelection;
import com.zhirong.liaohui.qo.MyPageRequest;
import com.zhirong.liaohui.service.UserActivitySelectionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户活动报名自定义选项(UserActivitySelection)表服务实现类
 *
 * @author makejava
 * @since 2023-10-13 11:10:54
 */
@Service("userActivitySelectionService")
public class UserActivitySelectionServiceImpl implements UserActivitySelectionService {
    @Resource
    private UserActivitySelectionDao userActivitySelectionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param activityId 主键
     * @return 实例对象
     */
    @Override
    public UserActivitySelection queryById(Integer activityId) {
        return this.userActivitySelectionDao.queryById(activityId);
    }

    /**
     * 分页查询
     *
     * @param userActivitySelection 筛选条件
     * @param
     * @return 查询结果
     */
    @Override
    public Page<UserActivitySelection> queryByPage(UserActivitySelection userActivitySelection, MyPageRequest pageRequests) {
        PageRequest pageRequestsDao =new PageRequest(pageRequests.getPage() - 1,pageRequests.getSize());
        long total = this.userActivitySelectionDao.count(userActivitySelection);
        return new PageImpl<>(this.userActivitySelectionDao.queryAllByLimit(userActivitySelection, pageRequestsDao), pageRequestsDao, total);
    }

    /**
     * 新增数据
     *
     * @param userActivitySelection 实例对象
     * @return 实例对象
     */
    @Override
    public UserActivitySelection insert(UserActivitySelection userActivitySelection) {
        this.userActivitySelectionDao.insert(userActivitySelection);
        return userActivitySelection;
    }

    /**
     * 修改数据
     *
     * @param userActivitySelection 实例对象
     * @return 实例对象
     */
    @Override
    public UserActivitySelection update(UserActivitySelection userActivitySelection) {
        this.userActivitySelectionDao.update(userActivitySelection);
        return this.queryById(userActivitySelection.getActivityId());
    }

    /**
     * 通过主键删除数据
     *
     * @param activityId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer activityId) {
        return this.userActivitySelectionDao.deleteById(activityId) > 0;
    }
}
