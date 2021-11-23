package com.liu.nba.mapper;

import com.liu.nba.pojo.Team;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TeamMapper {
    /*根据索引搜索所有球队的信息*/
    List<Team> getAllTeams(Connection connection, int startPage, int pageSize) throws IOException, SQLException;
    /*得到球队的数量*/
    int getTeamsCount(Connection connection) throws SQLException;
    /*根据球队名字模糊查询球队*/
    List<Team> getTeamsByName(Connection connection,String name, int startPage, int pageSize) throws SQLException;
}
