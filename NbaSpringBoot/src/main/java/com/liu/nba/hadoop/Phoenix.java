package com.liu.nba.hadoop;

import java.sql.*;

public class Phoenix {
    private static String url = "jdbc:phoenix:192.168.188.101,192.168.188.102,192.168.188.103:2181";
    private static String driver = "org.apache.phoenix.jdbc.PhoenixDriver";
    private static Connection connection;
    static {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            statement.execute("use \"nba\"");
            statement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
    //编写查询公共方法
    public static ResultSet execute(Connection connection,ResultSet resultSet,PreparedStatement preparedStatement,Object[] params,String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for(int i=0;i<params.length;i++)
        {
            preparedStatement.setObject(i+1,params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    //编写增删改公共方法
    public static int execute(Connection connection,String sql,Object[] params,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for(int i=0;i<params.length;i++)
        {
            preparedStatement.setObject(i+1,params[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }
    //释放资源
    public static boolean closeResource(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet)
    {
        boolean flag = true;
        if(resultSet!=null)
        {
            try {
                resultSet.close();
                //GC回收
                resultSet = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        if(preparedStatement!=null)
        {
            try {
                preparedStatement.close();
                //GC回收
                preparedStatement = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        if(connection!=null)
        {
            try {
                connection.close();
                //GC回收
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}
