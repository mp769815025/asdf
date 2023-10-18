package com.zhirong.liaohui.dao;

import com.zhirong.liaohui.entity.UserActivity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (UserActivity)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-13 09:47:40
 */
public interface UserActivityDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserActivity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param userActivity 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<UserActivity> queryAllByLimit(UserActivity userActivity, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param userActivity 查询条件
     * @return 总行数
     */
    long count(UserActivity userActivity);

    /**
     * 新增数据
     *
     * @param userActivity 实例对象
     * @return 影响行数
     */
    int insert(UserActivity userActivity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserActivity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserActivity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserActivity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserActivity> entities);

    /**
     * 修改数据
     *
     * @param userActivity 实例对象
     * @return 影响行数
     */
    int update(UserActivity userActivity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

