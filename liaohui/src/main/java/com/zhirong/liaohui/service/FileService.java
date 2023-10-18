package com.zhirong.liaohui.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {


    Map<String, String> upload(MultipartFile file);
}
