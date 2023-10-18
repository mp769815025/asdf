package com.zhirong.liaohui.controller;

import com.zhirong.liaohui.entity.UserActivity;
import com.zhirong.liaohui.qo.MyPageRequest;
import com.zhirong.liaohui.service.UserActivityService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserActivity)表控制层
 *
 * @author makejava
 * @since 2023-10-13 09:47:39
 */
@RestController
@Api(tags="用户活动中间表")
@RequestMapping("userActivity")
public class UserActivityController {
    /**
     * 服务对象
     */
    @Resource
    private UserActivityService userActivityService;

    /**
     * 分页查询
     *
     * @param userActivity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UserActivity>> queryByPage(UserActivity userActivity, MyPageRequest pageRequest) {
        return ResponseEntity.ok(this.userActivityService.queryByPage(userActivity, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<UserActivity> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userActivityService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param userActivity 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<UserActivity> add(@RequestBody UserActivity userActivity) {
        return ResponseEntity.ok(this.userActivityService.insert(userActivity));
    }

    /**
     * 编辑数据
     *
     * @param userActivity 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UserActivity> edit(@RequestBody UserActivity userActivity) {
        return ResponseEntity.ok(this.userActivityService.update(userActivity));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.userActivityService.deleteById(id));
    }

}

