package com.zhirong.liaohui.service.impl;

import com.zhirong.liaohui.dao.UserActivityDao;
import com.zhirong.liaohui.entity.UserActivity;
import com.zhirong.liaohui.qo.MyPageRequest;
import com.zhirong.liaohui.service.UserActivityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (UserActivity)表服务实现类
 *
 * @author makejava
 * @since 2023-10-13 09:47:41
 */
@Service("userActivityService")
public class UserActivityServiceImpl implements UserActivityService {
    @Resource
    private UserActivityDao userActivityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserActivity queryById(Integer id) {
        return this.userActivityDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param userActivity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<UserActivity> queryByPage(UserActivity userActivity, MyPageRequest pageRequests) {
        PageRequest pageRequestsDao =new PageRequest(pageRequests.getPage() - 1,pageRequests.getSize());
        long total = this.userActivityDao.count(userActivity);
        return new PageImpl<>(this.userActivityDao.queryAllByLimit(userActivity, pageRequestsDao), pageRequestsDao, total);
    }

    /**
     * 新增数据
     *
     * @param userActivity 实例对象
     * @return 实例对象
     */
    @Override
    public UserActivity insert(UserActivity userActivity) {
        this.userActivityDao.insert(userActivity);
        return userActivity;
    }

    /**
     * 修改数据
     *
     * @param userActivity 实例对象
     * @return 实例对象
     */
    @Override
    public UserActivity update(UserActivity userActivity) {
        this.userActivityDao.update(userActivity);
        return this.queryById(userActivity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userActivityDao.deleteById(id) > 0;
    }
}
