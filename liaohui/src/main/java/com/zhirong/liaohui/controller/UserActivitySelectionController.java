package com.zhirong.liaohui.controller;

import com.zhirong.liaohui.entity.UserActivitySelection;
import com.zhirong.liaohui.qo.MyPageRequest;
import com.zhirong.liaohui.service.UserActivitySelectionService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户活动报名自定义选项(UserActivitySelection)表控制层
 *
 * @author makejava
 * @since 2023-10-13 11:10:51
 */
@RestController
@Api(tags="用户活动报名自定义选项")
@RequestMapping("userActivitySelection")
public class UserActivitySelectionController {
    /**
     * 服务对象
     */
    @Resource
    private UserActivitySelectionService userActivitySelectionService;

    /**
     * 分页查询
     *
     * @param userActivitySelection 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<UserActivitySelection>> queryByPage(UserActivitySelection userActivitySelection, MyPageRequest pageRequest) {
        return ResponseEntity.ok(this.userActivitySelectionService.queryByPage(userActivitySelection, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById")
    public ResponseEntity<UserActivitySelection> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userActivitySelectionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param userActivitySelection 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<UserActivitySelection> add(@RequestBody UserActivitySelection userActivitySelection) {
        return ResponseEntity.ok(this.userActivitySelectionService.insert(userActivitySelection));
    }

    /**
     * 编辑数据
     *
     * @param userActivitySelection 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<UserActivitySelection> edit(@RequestBody UserActivitySelection userActivitySelection) {
        return ResponseEntity.ok(this.userActivitySelectionService.update(userActivitySelection));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete")
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.userActivitySelectionService.deleteById(id));
    }

}

