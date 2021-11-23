package com.liu.nba.service;

import com.liu.nba.pojo.Player;
import com.liu.nba.pojo.Team;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TeamService {
    /*获取所有的Team*/
    List<Team> getAlTeames(int startPage, int pageSize) throws SQLException, IOException;
    /*获取Team的数量*/
    int getTeamsCount() throws SQLException;
    /*根据球队名字模糊查询球队*/
    List<Team> getTeamsByName(String name,int startPage, int pageSize) throws SQLException;
}
