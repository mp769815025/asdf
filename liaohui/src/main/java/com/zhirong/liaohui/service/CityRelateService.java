package com.zhirong.liaohui.service;

import com.zhirong.liaohui.entity.CityRelate;
import com.zhirong.liaohui.model.CityModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (CityRelate)表服务接口
 *
 * @author makejava
 * @since 2023-10-12 12:00:31
 */
public interface CityRelateService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CityRelate queryById(Integer id);

    /**
     * 分页查询
     *
     * @param cityRelate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<CityRelate> queryByPage(CityRelate cityRelate, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param cityRelate 实例对象
     * @return 实例对象
     */
    CityRelate insert(CityRelate cityRelate);

    /**
     * 修改数据
     *
     * @param cityRelate 实例对象
     * @return 实例对象
     */
    CityRelate update(CityRelate cityRelate);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    CityModel selectCity();
}
