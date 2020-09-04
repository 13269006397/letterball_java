package com.letterball.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.letterball.common.Constants;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Repository
public class AliYunOssUtils {

    /**
     *  获得OSS实例
     */
    public OSS findOssClient(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GDE7quoACkrpCsLbskm";
        String accessKeySecret = "uhA8aN5o2uIKIERRoRKaeBqvVa2fui";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }

    /**
     * 上传文件(文件流方式)
     */
    public String AliYunOSSUpload(MultipartFile file, String upFilePath) throws IOException {
        // 上传的库名称
        String bucketName = "xinqiu-007";
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = upFilePath+ "/" + file.getOriginalFilename();
        String filePath= "";

        // 创建OSSClient实例。
        OSS ossClient = findOssClient();

        try {
            //上传文件流
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(bucketName, objectName, inputStream);
            // 取回上传到oss的文件路径 https://xinqiu-007.oss-cn-beijing.aliyuncs.com/edu_avatar/2019-06-29%20210413.jpg
            filePath = "https://" + bucketName + "." + endpoint + "/" + upFilePath + "/" + URLEncoder.encode(file.getOriginalFilename());

        }catch (Exception e){
            return Constants.DOWNLOAD_ERROR;
        }finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return filePath;
    }

}
