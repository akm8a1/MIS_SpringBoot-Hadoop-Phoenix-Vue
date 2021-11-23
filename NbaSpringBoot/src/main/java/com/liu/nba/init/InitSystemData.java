package com.liu.nba.init;


import com.liu.nba.hadoop.HiveUtils;

public class InitSystemData {
    public static void main(String[] args) throws Exception {
        createTable();
    }
    public static void createTable() throws Exception {
        String createBaseSQL = "CREATE TABLE players(\n" +
                "    rowkey int,\n" +
                "    player string,\n" +
                "    age int,\n" +
                "    rk int,\n" +
                "    position string,\n" +
                "    team string,\n" +
                "    salary_millions double,\n" +
                "    gp int,\n" +
                "    mp double,\n" +
                "    fga double,\n" +
                "    fg_percent double,\n" +
                "    three double,\n" +
                "    threea double,\n" +
                "    three_percent double,\n" +
                "    two double,\n" +
                "    twoa double,\n" +
                "    two_percent double,\n" +
                "    efg double,\n" +
                "    ft double,\n" +
                "    fta double,\n" +
                "    ft_percent double,\n" +
                "    orb double,\n" +
                "    drb double,\n" +
                "    trb double,\n" +
                "    ast double,\n" +
                "    stl double,\n" +
                "    blk double,\n" +
                "    tov double,\n" +
                "    pf double,\n" +
                "    points double,\n" +
                "    orpm double,\n" +
                "    drpm double,\n" +
                "    rpm double,\n" +
                "    wins_rpm double,\n" +
                "    pie double,\n" +
                "    w int,\n" +
                "    pace int,\n" +
                "    w_percentage double\n" +
                ")\n" +
                "STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'\n" +
                "WITH SERDEPROPERTIES (\"hbase.columns.mapping\" = \n" +
                "\":key,base:player,base:age,base:rk,base:position,base:team," +
                "base:salary_millions,performance:gp,performance:mp,performance:fg,performance:fga," +
                "performance:fg_percent,performance:three,performance:threea,performance:three_percent," +
                "performance:two,performance:twoa,performance:two_percent,performance:efg,performance:ft," +
                "performance:fta,performance:ft_percent,performance:orb,performance:drb,performance:trb," +
                "performance:ast,performance:stl,performance:blk,performance:tov,performance:pf," +
                "performance:points,advanced:orpm,advanced:drpm,advanced:rpm,advanced:wins_rpm,advanced:pie," +
                "advanced:w,advanced:pace,advanced:w_percentage\")\n" +
                "TBLPROPERTIES (\"hbase.table.name\" = \"nba:base\")";

    }
    public void loadData() {
        String sql = "load data inpath '/nba/data/player_data.dat";
    }
}
