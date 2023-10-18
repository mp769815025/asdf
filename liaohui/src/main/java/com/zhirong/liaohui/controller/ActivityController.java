package com.zhirong.liaohui.controller;

import com.zhirong.liaohui.entity.Activity;
import com.zhirong.liaohui.qo.ActivityRequest;
import com.zhirong.liaohui.qo.MyPageRequest;
import com.zhirong.liaohui.service.ActivityService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 活动(Activity)表控制层
 *
 * @author makejava
 * @since 2023-10-13 10:29:39
 */
@RestController
@Api(tags="活动")
@RequestMapping("activity")
public class ActivityController {
    /**
     * 服务对象
     */
    @Resource
    private ActivityService activityService;

    /**
     * 分页查询
     *
     * @param activity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<Activity>> queryByPage(Activity activity, MyPageRequest pageRequest) {
        return ResponseEntity.ok(this.activityService.queryByPage(activity, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public ResponseEntity<Activity> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.activityService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param  实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Activity> add(@RequestBody ActivityRequest request, HttpServletRequest servletRequest) {
        return ResponseEntity.ok(this.activityService.insert(request,servletRequest));
    }

    /**
     * 编辑数据
     *
     * @param activity 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResponseEntity<Activity> edit(@RequestBody ActivityRequest request) {
        return ResponseEntity.ok(this.activityService.update(request));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.activityService.deleteById(id));
    }

}

