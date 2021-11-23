package com.liu.nba.service;
import com.liu.nba.mapper.PlayerMapper;
import com.liu.nba.hadoop.Phoenix;
import com.liu.nba.pojo.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    private PlayerMapper dao;
    private Connection connection = Phoenix.getConnection();

    @Override
    public List<Player> getAllPlayers(int startPage,int pageSize) throws SQLException, IOException {
        return dao.getAllPlayers(connection,startPage,pageSize);
    }
    @Override
    public int getPlayersCount() throws SQLException {
        return dao.getPlayersCount(connection);
    }

    @Override
    public List<Player> getPlayersLike(String name, int startPage, int pageSize) throws SQLException {
        return dao.getPlayersLike(connection,name,startPage,pageSize);
    }

    @Override
    public List<Player> getMaxPlayer(String column) throws SQLException {
        return dao.getMaxPlayer(connection,column);
    }

    @Override
    public int getRankList(String column, Double value) throws SQLException {
        return dao.getRankList(connection,column,value);
    }
}
