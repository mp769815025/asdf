package com.zhirong.liaohui.service.impl;

import com.zhirong.liaohui.dao.CityRelateDao;
import com.zhirong.liaohui.entity.CityRelate;
import com.zhirong.liaohui.model.CityModel;
import com.zhirong.liaohui.service.CityRelateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CityRelate)表服务实现类
 *
 * @author makejava
 * @since 2023-10-12 12:00:31
 */
@Service("cityRelateService")
public class CityRelateServiceImpl implements CityRelateService {
    @Resource
    private CityRelateDao cityRelateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CityRelate queryById(Integer id) {
        return this.cityRelateDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cityRelate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<CityRelate> queryByPage(CityRelate cityRelate, PageRequest pageRequest) {
        long total = this.cityRelateDao.count(cityRelate);
        return new PageImpl<>(this.cityRelateDao.queryAllByLimit(cityRelate, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param cityRelate 实例对象
     * @return 实例对象
     */
    @Override
    public CityRelate insert(CityRelate cityRelate) {
        this.cityRelateDao.insert(cityRelate);
        return cityRelate;
    }

    /**
     * 修改数据
     *
     * @param cityRelate 实例对象
     * @return 实例对象
     */
    @Override
    public CityRelate update(CityRelate cityRelate) {
        this.cityRelateDao.update(cityRelate);
        return this.queryById(cityRelate.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.cityRelateDao.deleteById(id) > 0;
    }

    @Override
    public CityModel selectCity() {
        List<CityRelate> list=cityRelateDao.queryAll();
        CityModel model = new CityModel();
        model.setList(list);
        return model;
    }
}
