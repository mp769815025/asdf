package com.zhirong.liaohui.controller;

import com.zhirong.liaohui.service.FileService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;
@RestController
@Api(tags="文件上传")
@RequestMapping("FileUpload")
public class FileUploadController {



    @Resource
    private FileService fileService;
    /**
     * 文件上传
     */
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity singleFileUpload(@RequestPart(value = "myfile" ,required = false) MultipartFile myfile) {
        Map<String, String> map = fileService.upload(myfile);
        return ResponseEntity.ok(map);
    }


}
