package com.zhirong.liaohui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping({"/"})
public class WxConfigController {

        @RequestMapping({"MP_verify_HIAtcXITTe2LUuun.txt"})
        private void returnConfigFile(HttpServletResponse response) throws IOException {
            String filePath = "D:\\liaohui\\liaohui\\MP_verify_HIAtcXITTe2LUuun.txt";
                OutputStream outputStream=response.getOutputStream();
            // 设置content-type属性
            response.setContentType("text/plain");

            // 设置content-disposition属性
            response.setHeader("Content-Disposition", "attachment;filename=\"" + "MP_verify_HIAtcXITTe2LUuun.txt" + "\"");

            FileInputStream fileInputStream = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // 关闭输入流和输出流
            fileInputStream.close();
            outputStream.close();

        }

    }

