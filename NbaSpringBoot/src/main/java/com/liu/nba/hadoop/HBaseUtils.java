package com.liu.nba.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
public class HBaseUtils {
    private static Configuration configuration;
    private static Connection connection;
    private static Admin admin;
    //初始化
    public static void init(){
        try {
            configuration = HBaseConfiguration.create();
            //HBase在HDFS中的位置
            configuration.set("hbase.rootdir", "hdfs://luyuandong:9000/hbase");
            //集群信息
            configuration.set("hbase.zookeeper.quorum","luyuandong,slave1,slave2");
            //工厂模式创建链接
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
    public static Admin getAdmin() {
        return admin;
    }
    //关闭连接
    public static void close() {
        try {
            if (admin!=null) {
                admin.close();
            }
            if (null != connection) {
                configuration.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //表是否存在
    public static boolean isExist(String name) throws IOException {
        //参数必须是TableName类型 ，使用valueOf接收String参数
        return admin.tableExists(TableName.valueOf(name));
    }

    //创建表
    //cfs是可变形参 ...表示多个参数
    public static void createTable(String tableName,String... cfs) throws IOException {
        if (cfs.length<=0) {
            System.out.println("未输入列族信息");
            return ;
        }
        //判断表是否存在
        if (isExist(tableName)) {
            System.out.println("该表已存在");
            return ;
        }
        //创建表描述器
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        //添加列族
        for (String cf : cfs) {
            //通过列族描述器加入列族
            hTableDescriptor.addFamily(new HColumnDescriptor(cf));
        }
        //创建表
        admin.createTable(hTableDescriptor);
        System.out.println("创建表成功");
    }
    //删除表
    public static boolean deleteTable(String tableName) throws IOException {
        //判断表是否存在
        if (!isExist(tableName)) {
            System.out.println("该表不存在");
            return false;
        }
        //disable
        admin.disableTable(TableName.valueOf(tableName));
        //drop
        admin.deleteTable(TableName.valueOf(tableName));
        return true;
    }
    //创建命名空间
    public static boolean createNamespace(String name) {
        //1.创建命名空间描述器 通过build构建
        NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(name).build();
        //2.创建命名空间
        try {
            admin.createNamespace(namespaceDescriptor);
        } catch (NamespaceExistException e) { //只能通过捕获异常的方式来检测命名空间是否存在
            System.out.println("命名空间已存在");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    //插入数据
    public static boolean insert(String tableName,String rowKey,String cf,String col,String val) {
        //获取表对象
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            //创建Put对象 , 参数为rowKey
            Put put = new Put(Bytes.toBytes(rowKey));
            //给put对象赋值
            put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(col),Bytes.toBytes(val));
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    //获取数据（get）
    //params是可变参数
    public static boolean getDate(String tableName,String... params) {
        try {
            //1.获取表对象
            Table table = connection.getTable(TableName.valueOf(tableName));
            //2.根据传入的参数数量创建get对象
            if (params.length<=0) {
                System.out.println("参数不足");
                return false;
            }
            Get get = new Get(Bytes.toBytes(params[0]));
            if (params.length==2) {
                get.addFamily(Bytes.toBytes(params[1]));
            } else if (params.length==3) {
                get.addColumn(Bytes.toBytes(params[1]),Bytes.toBytes(params[2]));
            } else if (params.length==4) { //设置返回的版本数，如果setNaxVersions不设置参数，会返回所有的记录
                get.setMaxVersions(Integer.valueOf(params[3]));
            }
            //3.获取数据
            Result result = table.get(get);
            //4.解析result并打印
            for (Cell cell : result.rawCells()) {
                //5.打印数据
                //CellUtil是处理Cell的类，cloneXXX会取出来对应的属性，返回Bytes[] , 需要通过toString()进行处理
                System.out.println("colFamily:" + Bytes.toString(CellUtil.cloneFamily(cell)));
                System.out.println("column:" + Bytes.toString(CellUtil.cloneQualifier(cell)));
                System.out.println("value:"+Bytes.toString(CellUtil.cloneValue(cell)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    //获取数据scan 必须输入表名，scan查询的最大粒度数据是全表。get方式可以设置的属性，scan也可以设置，例如MaxVersions
    //params是可变参数，里面有startrow与stoprow
    public static void scanData(String tableName,String... params) {
        try {
            //1.获取表对象
            Table table = connection.getTable(TableName.valueOf(tableName));
            //2.构建Scan对象
            Scan scan = new Scan();
            if (params.length==2) {
                scan = new Scan(Bytes.toBytes(params[0]),Bytes.toBytes(params[1]));
            }
            //3.扫描表
            ResultScanner results = table.getScanner(scan);
            //4.解析resultScanner , 返回一个个的result，每一个result对应了一行记录
            for (Result result : results) {
                //5.解析result并打印
                for (Cell cell : result.rawCells()) {
                    //5.打印数据
                    //CellUtil是处理Cell的类，cloneXXX会取出来对应的属性，返回Bytes[] , 需要通过toString()进行处理
                    System.out.println("rowKey:" + Bytes.toString(CellUtil.cloneFamily(cell)));
                    System.out.println("colFamily:" + Bytes.toString(CellUtil.cloneFamily(cell)));
                    System.out.println("column:" + Bytes.toString(CellUtil.cloneQualifier(cell)));
                    System.out.println("value:"+Bytes.toString(CellUtil.cloneValue(cell)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //删除数据列族（最新时间戳）
    public static void deleteFamilies(String tableName, String rowKey,List<String> families) throws IOException {
        //1.获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        //2.根据传入的参数构建删除对象
        if (StringUtils.isEmpty(rowKey)) {
            System.out.println("请输入rowKey");
            return ;
        }
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        for (String family : families) {
            delete.addFamily(Bytes.toBytes(family));
        }
        //3.执行删除操作
        table.delete(delete);
    }
    //删除列
    public static void deleteColumns(String tableName,String rowKey,String familyName,List<String> columns) throws IOException {
        //1.获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        //2.根据传入的参数构建删除对象
        if (StringUtils.isEmpty(rowKey))  {
            System.out.println("请输入rowKey");
            return ;
        }
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        for (String column : columns) {
            delete.addColumn(Bytes.toBytes(familyName),Bytes.toBytes(column));
        }
        table.delete(delete);
    }
    public static void deleteFamiliesVersions(String tableName, String rowKey,List<String> families) throws IOException {
        //1.获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        //2.根据传入的参数构建删除对象
        if (StringUtils.isEmpty(rowKey)) {
            System.out.println("请输入rowKey");
            return ;
        }
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        for (String family : families) {
            delete.addFamily(Bytes.toBytes(family));
        }
        //3.执行删除操作
        table.delete(delete);
    }
}
