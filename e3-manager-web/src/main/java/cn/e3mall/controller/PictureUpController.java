package cn.e3mall.controller;

import cn.e3mall.FastDfsUtils;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureUpController {
    @Value("${tracker_server}")
    String url;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map pictureUp(MultipartFile uploadFile) {
        String fakerUrl = url;
        Map map = new HashMap<>();
        try {
            //得到上传文件的字节数组
            byte[] bytes = uploadFile.getBytes();
            //获取上传文件的名字
            String originalFilename = uploadFile.getOriginalFilename();
            //获取上传文件的扩展名
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //调用工具类上传文件到文件服务器
            StorageClient storageClient = FastDfsUtils.getStorageClient();
            String[] strings = storageClient.upload_appender_file(bytes, ext, null);
            for (String str : strings) {
                url = url + str + "/";
            }
            int i = url.lastIndexOf("/");
            url = url.substring(0, i);
            map.put("error", 0);
            map.put("url", url);
            url = fakerUrl;
        } catch (Exception e) {
            map.put("error", 1);
            map.put("message", "图片上传失败");
        }

        return map;
    }
}
