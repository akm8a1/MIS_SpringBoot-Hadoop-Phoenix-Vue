package com.liu.nba.service;

import com.liu.nba.mapper.TeamMapper;
import com.liu.nba.hadoop.Phoenix;
import com.liu.nba.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{
    @Autowired
    private TeamMapper dao;
    private Connection connection = Phoenix.getConnection();

    @Override
    public List<Team> getAlTeames(int startPage, int pageSize) throws SQLException, IOException {
        List<Team> teams = dao.getAllTeams(connection,startPage,pageSize);
        return teams;
    }

    @Override
    public int getTeamsCount() throws SQLException {
        return dao.getTeamsCount(connection);
    }

    @Override
    public List<Team> getTeamsByName(String name,int startPage, int pageSize) throws SQLException {
        return dao.getTeamsByName(connection,name,startPage,pageSize);
    }
}
