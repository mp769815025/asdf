package com.zhirong.liaohui.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.zhirong.liaohui.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private String AccessKey_id="LTAI5tM26de338HKhNi1uAgR";

    private String AccessKey_Secret="FJMHZ8Q40xt64gqwRh6P5xW9ZfO7zD";

    private String bucket="java-liaohui";

    private String endPoint="oss-cn-chengdu.aliyuncs.com";
    /**
     * 参考SDK中的：Java->上传文件->简单上传->流式上传->上传文件流
     * @param file
     * @return
     */
    @Override
    public Map<String, String> upload(MultipartFile file) {

        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = endPoint;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = AccessKey_id;
        String accessKeySecret = AccessKey_Secret;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = bucket;

        // 文件名称
        String originalFilename = file.getOriginalFilename();
        String dateString = new DateTime().toString("yyyyMMdd");
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName =
                dateString
                        + "/" + UUID.randomUUID().toString().replace("-", "")
                        + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = file.getInputStream();
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // 设置该属性可以返回response。如果不设置，则返回的response为空。
            putObjectRequest.setProcess("true");
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);

            // 如果上传成功，则返回200。
            log.info(Integer.toString(result.getResponse().getStatusCode()));
            if(result.getResponse().getStatusCode() != 200){
                System.out.println("上传失败");
            }

            //返回图片路径
            //参考SDK中的：Java-> Java授权访问-> 生成以GET方法访问的签名URL
            // 设置URL过期时间为1小时，单位：毫秒
            Date expiration = new Date(new Date().getTime() + 60 * 60 * 1000);
            URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            String urlStr=url.toString();
            String subStr=urlStr.substring(0,urlStr.indexOf("?"));
            Map<String, String> map = new HashMap<>();
            map.put("url", subStr); //数据库存储
            return map;

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());

            oe.printStackTrace();

        }  catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}