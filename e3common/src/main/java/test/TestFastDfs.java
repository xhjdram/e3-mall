package test;

import org.csource.fastdfs.*;

public class TestFastDfs {
    public static StringBuffer str1;

    public static void main(String[] args) throws Exception {
//  创建全局配置文件
        //通过加载配置文件创建client对象
        ClientGlobal.init("D:\\e3-mall\\e3-manager\\e3-manager-service\\src\\main\\resources\\config.properties");
        //创建trackClient 对象
        TrackerClient trackerClient = new TrackerClient();
        //创建trackServer 对象
        TrackerServer connection = trackerClient.getConnection();
        //生命StroageServer
        StorageServer storageServer = null;
        //创建StorageClient对象
        StorageClient storageClient = new StorageClient(connection, storageServer);
        //使用stroageClient上传文件
        String[] jpgs = storageClient.upload_appender_file("C:\\Users\\dell3020mt-41\\Pictures\\W_2014_364_OVERLORD_II.jpg", "jpg", null);
        //遍历返回的路径
        for (String str : jpgs) {
            System.out.println(str + str1);
        }

    }
}
