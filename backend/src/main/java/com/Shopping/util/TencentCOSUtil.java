package com.Shopping.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;
@Component
public class TencentCOSUtil {

    private static String secretId = "AKIDMsXYVNDLRdlpz8UmGnMGJF3mLnfR8iAW";
    private static String secretKey = "HLFvOdeWowuiLrGWgdNvJWWacklfWIxj";
    private static String rootSrc = "https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/";
    private static String bucketAddr = "ap-shanghai";
    private static String bucketName = "shopping-1254803835";

    private static COSClient getCosClient() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2.1 设置存储桶的地域（上文获得）
        Region region = new Region(bucketAddr);
        ClientConfig clientConfig = new ClientConfig(region);
        // 2.2 使用https协议传输
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 返回COS客户端
        return cosClient;
    }

    public static String upLoadFile(MultipartFile file) {
        try {
            // 获取上传的文件的输入流
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            //获取文件的类型
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            //使用UUID工具  创建唯一名称
            String fileName = UUID.randomUUID().toString() + fileType;
            String key = "data/" + fileName;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setContentType(fileType);
            PutObjectResult putResult = getCosClient().putObject(bucketName, key, inputStream, objectMetadata);
            String url = rootSrc + key;
            getCosClient().shutdown();
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            // 发生IO异常、COS连接异常等，返回空
            return null;
        }
    }

}