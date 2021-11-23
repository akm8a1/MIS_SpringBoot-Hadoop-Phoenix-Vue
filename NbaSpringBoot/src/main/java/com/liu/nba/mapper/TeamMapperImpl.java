package com.liu.nba.mapper;

import com.liu.nba.hadoop.Phoenix;
import com.liu.nba.pojo.Team;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class TeamMapperImpl implements TeamMapper {
    @Override
    public List<Team> getAllTeams(Connection connection, int startPage, int pageSize) throws IOException, SQLException {
        PreparedStatement preparedStatement = null;
        List<Team> teams = new ArrayList<>();
        ResultSet resultSet = null;
        if(connection!=null)
        {
            String sql = "select * from \"team\" order by to_number(\"rowkey\") limit "+pageSize+" offset "+(startPage-1)*pageSize;
            Object[] params = {};
            resultSet = Phoenix.execute(connection,resultSet,preparedStatement,params,sql);
            while(resultSet.next())
            {
                Team team = new Team();
                team.set(
                        resultSet.getString("rowkey"),
                        resultSet.getString("team"),
                        Integer.parseInt(resultSet.getString("totalscore")),
                        Integer.parseInt(resultSet.getString("yearscore")),
                        Double.parseDouble(resultSet.getString("perscore")),
                        Integer.parseInt(resultSet.getString("salary"))
                );
                teams.add(team);
            }
            Phoenix.closeResource(null,preparedStatement,resultSet);
        }
        return teams;
    }

    @Override
    public int getTeamsCount(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        int count = 0;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql = "select count(1) from \"team\"";
            Object[] params = {};
            resultSet = Phoenix.execute(connection, resultSet, preparedStatement, params, sql);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        }
        Phoenix.closeResource(null,preparedStatement,resultSet);
        return count;
    }

    @Override
    public List<Team> getTeamsByName(Connection connection,String name, int startPage, int pageSize) throws SQLException {
        PreparedStatement preparedStatement = null;
        List<Team> teams = new ArrayList<>();
        ResultSet resultSet = null;
        if(connection!=null)
        {
            String sql = "";
            if ("".equals(name)) {
                sql = "select * from \"team\" order by to_number(\"rowkey\") limit "+pageSize+" offset "+(startPage-1)*pageSize;
            } else {
                sql = "select * from \"team\" where \"team\" like '%"+name+"%' order by to_number(\"rowkey\") limit "+pageSize+" offset "+(startPage-1)*pageSize;
            }
            System.out.println(sql);
            Object[] params = {};
            resultSet = Phoenix.execute(connection,resultSet,preparedStatement,params,sql);
            while(resultSet.next())
            {
                Team team = new Team();
                team.set(
                        resultSet.getString("rowkey"),
                        resultSet.getString("team"),
                        Integer.parseInt(resultSet.getString("totalscore")),
                        Integer.parseInt(resultSet.getString("yearscore")),
                        Double.parseDouble(resultSet.getString("perscore")),
                        Integer.parseInt(resultSet.getString("salary"))
                );
                teams.add(team);
            }
            Phoenix.closeResource(null,preparedStatement,resultSet);
        }
        return teams;
    }
}
