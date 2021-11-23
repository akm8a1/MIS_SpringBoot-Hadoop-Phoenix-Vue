package com.liu.nba.hadoop;

import org.springframework.stereotype.Repository;

import java.sql.*;

public class HiveUtils {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String Url = "jdbc:hive2://192.168.188.101:10000/nba"; //hive中配置的hiveserver2的地址，最后的hive是要操作的数据库
    private static String username = "hadoop";
    private static String password = "180018ly";
    private static Connection connection = null;
    private static Statement statement = null ;
    private static ResultSet rs = null;
    static {
        try{
            Class.forName(driverName);
            connection = DriverManager.getConnection(Url,username,password);
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //创建数据库
    public static void createDatabase(String sql) throws SQLException {
        statement.execute(sql);
    }
    //查询所有的数据库
    public static void showDatabases() throws SQLException {
        statement.execute("use nba");
        String sql = "show databases";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }
    //创建表
    public void createTable(String sql) throws SQLException {
        statement.execute("use nba");
        statement.execute(sql);
    }
    //查询所有的表
    public void showTables() throws SQLException {
        statement.execute("use nba");
        String sql = "show tables";
        rs = statement.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString(1));
        }
    }
    //查看表结构
    public static void descTable() throws SQLException {
        String sql = "desc emp";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            System.out.println("columnName:" + rs.getString(1) + "\n" + "dataType:" + rs.getString(2));
            //还可以有第三个输出，comment属性注释
        }
    }
    //加载数据
    public static void loadData() throws SQLException {
        String localPath = "/usr/local/hive/emp.txt"; //主机中的路径
        String hdfsPath = "/data/emp.data"; //hdfs路径
        String loadLocalFile = "load data local inpath "+localPath + " into table emp";
        String loadHdfsFile = "load data inpath "+hdfsPath + " into table emp";
        statement.execute(loadHdfsFile);
    }
    //查询数据
    public static void selectData() throws SQLException {
        String sql = "select * from emp";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            System.out.println("empno:" + rs.getString("empno") + "\n" + "eanme:" + rs.getString("ename"));
        }
    }
    //释放资源
    public static void close() throws SQLException {
        if (rs!=null) {
            rs.close();
        }
        if (statement!=null) {
            statement.close();
        }
        if (connection!=null) {
            connection.close();
        }
    }
}

