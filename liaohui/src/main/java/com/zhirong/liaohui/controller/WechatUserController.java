package com.zhirong.liaohui.controller;

import com.zhirong.liaohui.entity.WechatUser;
import com.zhirong.liaohui.service.WechatUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 小程序用户表(WechatUser)表控制层
 *
 * @author makejava
 * @since 2023-10-09 15:27:47
 */
@RestController
@RequestMapping("wechatUser")
public class WechatUserController {
    /**
     * 服务对象
     */
    @Resource
    private WechatUserService wechatUserService;

//    /**
//     * 分页查询
//     *
//     * @param wechatUser 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @GetMapping
//    public ResponseEntity<Page<WechatUser>> queryByPage(WechatUser wechatUser, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.wechatUserService.queryByPage(wechatUser, pageRequest));
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<WechatUser> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.wechatUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param wechatUser 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<WechatUser> add(WechatUser wechatUser) {
        return ResponseEntity.ok(this.wechatUserService.insert(wechatUser));
    }

    /**
     * 编辑数据
     *
     * @param wechatUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<WechatUser> edit(WechatUser wechatUser) {
        return ResponseEntity.ok(this.wechatUserService.update(wechatUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.wechatUserService.deleteById(id));
    }

}

