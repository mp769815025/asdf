package com.zhirong.liaohui.service;

import com.zhirong.liaohui.entity.FriendsFound;
import com.zhirong.liaohui.qo.MyPageRequest;
import org.springframework.data.domain.Page;

/**
 * (FriendsFound)表服务接口
 *
 * @author makejava
 * @since 2023-10-10 11:44:26
 */
public interface FriendsFoundService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FriendsFound queryById(Integer id);

    /**
     * 分页查询
     *
     * @param friendsFound 筛选条件
     * @param       分页对象
     * @return 查询结果
     */
    Page<FriendsFound> queryByPage(FriendsFound friendsFound, MyPageRequest pageRequests);

    /**
     * 新增数据
     *
     * @param friendsFound 实例对象
     * @return 实例对象
     */
    FriendsFound insert(FriendsFound friendsFound);

    /**
     * 修改数据
     *
     * @param friendsFound 实例对象
     * @return 实例对象
     */
    FriendsFound update(FriendsFound friendsFound);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


//    Object selectCity();
}
