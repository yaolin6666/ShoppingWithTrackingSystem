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
import java.net.URL;
import java.util.UUID;
//交给IOC容器管理
@Component
public class TencentCOSUtil {

    // COS的SecretId
    private static String secretId = "AKIDt35b0FI7mHa1UyT0Qgsz027CLPL5mKjz";
    // COS的SecretKey
    private static String secretKey = "VrLirmWEoG7tUyIOMLOXekmeaq4NZbWJ";
    //文件上传后访问路径的根路径，后面要最佳文件名字与类型
    private static String rootSrc = "https://shopping-1254803835.cos.ap-shanghai.myqcloud.com/";
    //上传的存储桶的地域，可参考根路径https://qq-test-1303******.cos.地域.myqcloud.com,此参数在COS的后台能查询。
    private static String bucketAddr = "ap-shanghai";
    //存储桶的名字，是自己在存储空间自己创建的，我创建的名字是：qq-test-1303******
    private static String bucketName = "shopping-1254803835";

    /**
     * 1.调用静态方法getCosClient()就会获得COSClient实例
     * 2.本方法根据永久密钥初始化 COSClient的，官方是不推荐，官方推荐使用临时密钥，是可以限制密钥使用权限，创建cred时有些区别
     *
     * @return COSClient实例
     */
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