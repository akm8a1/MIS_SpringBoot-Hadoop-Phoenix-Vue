package com.liu.nba.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

public class HdfsUtils {
    private static FileSystem fileSystem;
    static {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://192.168.188.101:9000"); //设置要查找的地方
        conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");
        try {
            fileSystem = FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static FileSystem getFileSystem() {
        return fileSystem;
    }
    //关闭HDFS资源和连接
    public static void close() throws IOException {
        fileSystem.close();
    }
    /*创建文件夹*/
    public static boolean mkdir(String path) throws IOException {
        Path srcPath = new Path(path);
        boolean isOk = fileSystem.mkdirs(srcPath);
        return isOk;
    }
    /*上传文件*/
    public static void uploadFile(String path,String uploadPath) throws Exception {
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(uploadPath)) {
            return;
        }
        FileSystem fs = getFileSystem();
        //上传路径
        Path clientPath = new Path(path);
        //目标路径
        Path serverPath = new Path(uploadPath);
        //调用文件系统的文件复制方法，第一个参数是否删除原文件true为删除，默认为false
        fs.copyFromLocalFile(false, clientPath, serverPath);
    }
}
