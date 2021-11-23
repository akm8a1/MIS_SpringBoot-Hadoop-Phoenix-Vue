package com.liu.nba.service;

import com.liu.nba.pojo.Player;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PlayerService {
    /*获取所有的Players*/
    List<Player> getAllPlayers(int startPage,int pageSize) throws SQLException, IOException;
    /*获取player的数量*/
    int getPlayersCount() throws SQLException;
    /*根据球员名称查询球员*/
    List<Player> getPlayersLike(String name, int startPage, int pageSize) throws SQLException;
    /*求某项最高的数值*/
    List<Player> getMaxPlayer(String column) throws SQLException;
    /*求某位球员所有数据的排名*/
    int getRankList(String column,Double value) throws SQLException;
}
