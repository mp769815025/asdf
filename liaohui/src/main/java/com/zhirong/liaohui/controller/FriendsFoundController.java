package com.zhirong.liaohui.controller;

import com.zhirong.liaohui.entity.FriendsFound;
import com.zhirong.liaohui.model.CityModel;
import com.zhirong.liaohui.qo.MyPageRequest;
import com.zhirong.liaohui.service.CityRelateService;
import com.zhirong.liaohui.service.FriendsFoundService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (FriendsFound)表控制层
 *
 * @author makejava
 * @since 2023-10-10 11:44:21
 */
@RestController
@Api(tags="找搭子")
@RequestMapping("friendsFound")
public class FriendsFoundController {
    /**
     * 服务对象
     */
    @Resource
    private FriendsFoundService friendsFoundService;

    @Resource
    private CityRelateService cityRelateService;

    /**
     * 分页查询
     *
     * @param friendsFound 筛选条件
     * @param
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResponseEntity<Page<FriendsFound>> queryByPage(FriendsFound friendsFound, MyPageRequest pageRequest) {
        return ResponseEntity.ok(this.friendsFoundService.queryByPage(friendsFound, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById")
    public ResponseEntity<FriendsFound> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.friendsFoundService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param friendsFound 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<FriendsFound> add(@RequestBody FriendsFound friendsFound) {
        return ResponseEntity.ok(this.friendsFoundService.insert(friendsFound));
    }

    /**
     * 编辑数据
     *
     * @param friendsFound 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResponseEntity<FriendsFound> edit(@RequestBody FriendsFound friendsFound) {
        return ResponseEntity.ok(this.friendsFoundService.update(friendsFound));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.friendsFoundService.deleteById(id));
    }

    @GetMapping("/selectCity")
    public ResponseEntity<CityModel> selectCity() {
        return ResponseEntity.ok(this.cityRelateService.selectCity());
    }

}

