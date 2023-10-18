package com.zhirong.liaohui.dao;

import com.zhirong.liaohui.entity.WechatUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序用户表(WechatUser)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-09 15:27:49
 */
public interface WechatUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WechatUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param wechatUser 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
//    List<WechatUser> queryAllByLimit(WechatUser wechatUser, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param wechatUser 查询条件
     * @return 总行数
     */
    long count(WechatUser wechatUser);

    /**
     * 新增数据
     *
     * @param wechatUser 实例对象
     * @return 影响行数
     */
    int insert(WechatUser wechatUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WechatUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WechatUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WechatUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WechatUser> entities);

    /**
     * 修改数据
     *
     * @param wechatUser 实例对象
     * @return 影响行数
     */
    int updateByOpenid(WechatUser wechatUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    WechatUser getUserByOpenId(String openId);

}

