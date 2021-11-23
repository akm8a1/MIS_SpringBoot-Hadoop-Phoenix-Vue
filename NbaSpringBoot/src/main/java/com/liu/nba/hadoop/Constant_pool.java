package com.liu.nba.hadoop;

public class Constant_pool {
    //系统在HDFS中的目录
    public static String PROJECT_DFS_PATH = "/nba";
    //存放球星图片的HDFS目录
    public static String PROJECT_PLAYERS_DFS_PATH = "/nba/pictures/";
    //存放原始数据的HDFS目录
    public static String PROJECT_DATA_DFS_PATH = "/nba/data/";
    //存放了原始数据的本地目录
    public static String ORIGINAL_NBA_DATA_PATH = "/usr/local/nbadata/";
    //球员文件
    public static String PLAYERs_DATA_NAME = "nba_2017_players_data.csv";
    //球队文件
    public static String TEAMS_DATA_NAME = "nba_2017_teams_data.csv";
    //hbase表名
    public static String HBASE_TABLE_NAME= "nba:player";

}
