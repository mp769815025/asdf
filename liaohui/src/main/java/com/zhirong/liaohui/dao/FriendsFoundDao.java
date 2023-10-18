package com.zhirong.liaohui.dao;

import com.zhirong.liaohui.entity.FriendsFound;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (FriendsFound)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-10 11:44:22
 */
public interface FriendsFoundDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FriendsFound queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param friendsFound 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<FriendsFound> queryAllByLimit(@Param(value = "friendsFound") FriendsFound friendsFound, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param friendsFound 查询条件
     * @return 总行数
     */
    long count(FriendsFound friendsFound);

    /**
     * 新增数据
     *
     * @param friendsFound 实例对象
     * @return 影响行数
     */
    int insert(FriendsFound friendsFound);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FriendsFound> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<FriendsFound> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FriendsFound> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<FriendsFound> entities);

    /**
     * 修改数据
     *
     * @param friendsFound 实例对象
     * @return 影响行数
     */
    int update(FriendsFound friendsFound);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    List<FriendsFound> queryAll();
}

