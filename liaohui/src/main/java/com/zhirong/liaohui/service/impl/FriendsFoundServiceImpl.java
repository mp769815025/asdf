package com.zhirong.liaohui.service.impl;

import com.zhirong.liaohui.dao.FriendsFoundDao;
import com.zhirong.liaohui.entity.FriendsFound;
import com.zhirong.liaohui.qo.MyPageRequest;
import com.zhirong.liaohui.service.FriendsFoundService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (FriendsFound)表服务实现类
 *
 * @author makejava
 * @since 2023-10-10 11:44:27
 */
@Service("friendsFoundService")
public class FriendsFoundServiceImpl implements FriendsFoundService {
    @Resource
    private FriendsFoundDao friendsFoundDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FriendsFound queryById(Integer id) {
        return this.friendsFoundDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param friendsFound 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<FriendsFound> queryByPage(FriendsFound friendsFound, MyPageRequest pageRequests) {
        PageRequest pageRequestsDao =new PageRequest(pageRequests.getPage() - 1,pageRequests.getSize());
        long total = this.friendsFoundDao.count(friendsFound);
        return new PageImpl<>(this.friendsFoundDao.queryAllByLimit(friendsFound, pageRequestsDao), pageRequestsDao, total);
    }

    /**
     * 新增数据
     *
     * @param friendsFound 实例对象
     * @return 实例对象
     */
    @Override
    public FriendsFound insert(FriendsFound friendsFound) {
        this.friendsFoundDao.insert(friendsFound);
        return friendsFound;
    }

    /**
     * 修改数据
     *
     * @param friendsFound 实例对象
     * @return 实例对象
     */
    @Override
    public FriendsFound update(FriendsFound friendsFound) {
        this.friendsFoundDao.update(friendsFound);
        return this.queryById(friendsFound.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.friendsFoundDao.deleteById(id) > 0;
    }
}
