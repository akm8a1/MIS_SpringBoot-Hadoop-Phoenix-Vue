package com.liu.nba.mapper;

import com.liu.nba.pojo.Player;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PlayerMapper {
    /*搜索所有球员的数据*/
    List<Player> getAllPlayers(Connection connection,@Param("startPage") int startPage,@Param("pageSize") int pageSize) throws IOException, SQLException;
    /*获取数据条数*/
    int getPlayersCount(Connection connection) throws SQLException;
    /*根据球员名称查询球员*/
    List<Player> getPlayersLike(Connection connection,@Param("name") String name,@Param("startPage") int startPage,@Param("pageSize") int pageSize) throws SQLException;
    /*求某项最高的数值*/
    List<Player> getMaxPlayer(Connection connection,String column) throws SQLException;
    /*求某位球员所有数据的排名*/
    int getRankList(Connection connection,String column,Double value) throws SQLException;
}
