package com.liu.nba.mapper;

import com.liu.nba.hadoop.Phoenix;
import com.liu.nba.pojo.Player;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerMapperImpl implements PlayerMapper {
    @Override
    public List<Player> getAllPlayers(Connection connection,int startPage,int pageSize) throws IOException, SQLException {
        PreparedStatement preparedStatement = null;
        /*查询记录数*/
        List<Player> players = new ArrayList<>();
        ResultSet resultSet = null;
        if(connection!=null)
        {
            String sql = "select * from \"player\" order by to_number(\"rk\") limit "+pageSize+" offset "+(startPage-1)*pageSize;
            Object[] params = {};
            resultSet = Phoenix.execute(connection,resultSet,preparedStatement,params,sql);
            while(resultSet.next())
            {
                Player player = new Player();
                player.set(
                        resultSet.getString("rowkey"),
                        resultSet.getString("player"),
                        Integer.parseInt(resultSet.getString("age")),
                        Integer.parseInt(resultSet.getString("rk")),
                        resultSet.getString("position"),
                        resultSet.getString("team"),
                        Double.parseDouble(resultSet.getString("salary_millions")),
                        Integer.parseInt(resultSet.getString("gp")),
                        Double.parseDouble(resultSet.getString("mp")),
                        Double.parseDouble(resultSet.getString("fg")),
                        Double.parseDouble(resultSet.getString("fga")),
                        Double.parseDouble(resultSet.getString("fg_percent")),
                        Double.parseDouble(resultSet.getString("three")),
                        Double.parseDouble(resultSet.getString("threea")),
                        Double.parseDouble(resultSet.getString("three_percent")),
                        Double.parseDouble(resultSet.getString("two")),
                        Double.parseDouble(resultSet.getString("twoa")),
                        Double.parseDouble(resultSet.getString("two_percent")),
                        Double.parseDouble(resultSet.getString("efg")),
                        Double.parseDouble(resultSet.getString("ft")),
                        Double.parseDouble(resultSet.getString("fta")),
                        Double.parseDouble(resultSet.getString("ft_percent")),
                        Double.parseDouble(resultSet.getString("orb")),
                        Double.parseDouble(resultSet.getString("drb")),
                        Double.parseDouble(resultSet.getString("trb")),
                        Double.parseDouble(resultSet.getString("ast")),
                        Double.parseDouble(resultSet.getString("stl")),
                        Double.parseDouble(resultSet.getString("blk")),
                        Double.parseDouble(resultSet.getString("tov")),
                        Double.parseDouble(resultSet.getString("pf")),
                        Double.parseDouble(resultSet.getString("points")),
                        Double.parseDouble(resultSet.getString("orpm")),
                        Double.parseDouble(resultSet.getString("drpm")),
                        Double.parseDouble(resultSet.getString("rpm")),
                        Double.parseDouble(resultSet.getString("wins_rpm")),
                        Double.parseDouble(resultSet.getString("pie")),
                        Integer.parseInt(resultSet.getString("w")),
                        Double.parseDouble(resultSet.getString("pace")),
                        Double.parseDouble(resultSet.getString("w_percentage"))
                );
                players.add(player);
            }
            Phoenix.closeResource(null,preparedStatement,resultSet);
        }
        return players;
    }

    @Override
    public int getPlayersCount(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        int count = 0;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql = "select count(1) from \"player\"";
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
    public List<Player> getPlayersLike(Connection connection, String name, int startPage, int pageSize) throws SQLException {
        PreparedStatement preparedStatement = null;
        /*查询记录数*/
        List<Player> players = new ArrayList<>();
        ResultSet resultSet = null;
        if(connection!=null)
        {
            String sql = "";
            if ("".equals(name)) {
                sql = "select * from \"player\" order by to_number(\"rk\") limit "+pageSize+" offset "+(startPage-1)*pageSize;
            } else {
                sql = "select * from \"player\" where \"player\" like '%"+name+"%' order by to_number(\"rk\") limit "+pageSize+" offset "+(startPage-1)*pageSize;
            }
            Object[] params = {};
            resultSet = Phoenix.execute(connection,resultSet,preparedStatement,params,sql);
            while(resultSet.next())
            {
                Player player = new Player();
                player.set(
                        resultSet.getString("rowkey"),
                        resultSet.getString("player"),
                        Integer.parseInt(resultSet.getString("age")),
                        Integer.parseInt(resultSet.getString("rk")),
                        resultSet.getString("position"),
                        resultSet.getString("team"),
                        Double.parseDouble(resultSet.getString("salary_millions")),
                        Integer.parseInt(resultSet.getString("gp")),
                        Double.parseDouble(resultSet.getString("mp")),
                        Double.parseDouble(resultSet.getString("fg")),
                        Double.parseDouble(resultSet.getString("fga")),
                        Double.parseDouble(resultSet.getString("fg_percent")),
                        Double.parseDouble(resultSet.getString("three")),
                        Double.parseDouble(resultSet.getString("threea")),
                        Double.parseDouble(resultSet.getString("three_percent")),
                        Double.parseDouble(resultSet.getString("two")),
                        Double.parseDouble(resultSet.getString("twoa")),
                        Double.parseDouble(resultSet.getString("two_percent")),
                        Double.parseDouble(resultSet.getString("efg")),
                        Double.parseDouble(resultSet.getString("ft")),
                        Double.parseDouble(resultSet.getString("fta")),
                        Double.parseDouble(resultSet.getString("ft_percent")),
                        Double.parseDouble(resultSet.getString("orb")),
                        Double.parseDouble(resultSet.getString("drb")),
                        Double.parseDouble(resultSet.getString("trb")),
                        Double.parseDouble(resultSet.getString("ast")),
                        Double.parseDouble(resultSet.getString("stl")),
                        Double.parseDouble(resultSet.getString("blk")),
                        Double.parseDouble(resultSet.getString("tov")),
                        Double.parseDouble(resultSet.getString("pf")),
                        Double.parseDouble(resultSet.getString("points")),
                        Double.parseDouble(resultSet.getString("orpm")),
                        Double.parseDouble(resultSet.getString("drpm")),
                        Double.parseDouble(resultSet.getString("rpm")),
                        Double.parseDouble(resultSet.getString("wins_rpm")),
                        Double.parseDouble(resultSet.getString("pie")),
                        Integer.parseInt(resultSet.getString("w")),
                        Double.parseDouble(resultSet.getString("pace")),
                        Double.parseDouble(resultSet.getString("w_percentage"))
                );
                players.add(player);
            }
            Phoenix.closeResource(null,preparedStatement,resultSet);
        }
        return players;
    }

    @Override
    public List<Player> getMaxPlayer(Connection connection, String column) throws SQLException {
        PreparedStatement preparedStatement = null;
        /*查询记录数*/
        List<Player> players = new ArrayList<>();
        ResultSet resultSet = null;
        if(connection!=null)
        {
            String sql = "select * from \"player\" where to_number(\""+column+"\")=(select max(to_number(\""+column+"\")) from \"player\")";
            System.out.println(sql);
            Object[] params = {};
            resultSet = Phoenix.execute(connection,resultSet,preparedStatement,params,sql);
            while(resultSet.next())
            {
                Player player = new Player();
                player.set(
                        resultSet.getString("rowkey"),
                        resultSet.getString("player"),
                        Integer.parseInt(resultSet.getString("age")),
                        Integer.parseInt(resultSet.getString("rk")),
                        resultSet.getString("position"),
                        resultSet.getString("team"),
                        Double.parseDouble(resultSet.getString("salary_millions")),
                        Integer.parseInt(resultSet.getString("gp")),
                        Double.parseDouble(resultSet.getString("mp")),
                        Double.parseDouble(resultSet.getString("fg")),
                        Double.parseDouble(resultSet.getString("fga")),
                        Double.parseDouble(resultSet.getString("fg_percent")),
                        Double.parseDouble(resultSet.getString("three")),
                        Double.parseDouble(resultSet.getString("threea")),
                        Double.parseDouble(resultSet.getString("three_percent")),
                        Double.parseDouble(resultSet.getString("two")),
                        Double.parseDouble(resultSet.getString("twoa")),
                        Double.parseDouble(resultSet.getString("two_percent")),
                        Double.parseDouble(resultSet.getString("efg")),
                        Double.parseDouble(resultSet.getString("ft")),
                        Double.parseDouble(resultSet.getString("fta")),
                        Double.parseDouble(resultSet.getString("ft_percent")),
                        Double.parseDouble(resultSet.getString("orb")),
                        Double.parseDouble(resultSet.getString("drb")),
                        Double.parseDouble(resultSet.getString("trb")),
                        Double.parseDouble(resultSet.getString("ast")),
                        Double.parseDouble(resultSet.getString("stl")),
                        Double.parseDouble(resultSet.getString("blk")),
                        Double.parseDouble(resultSet.getString("tov")),
                        Double.parseDouble(resultSet.getString("pf")),
                        Double.parseDouble(resultSet.getString("points")),
                        Double.parseDouble(resultSet.getString("orpm")),
                        Double.parseDouble(resultSet.getString("drpm")),
                        Double.parseDouble(resultSet.getString("rpm")),
                        Double.parseDouble(resultSet.getString("wins_rpm")),
                        Double.parseDouble(resultSet.getString("pie")),
                        Integer.parseInt(resultSet.getString("w")),
                        Double.parseDouble(resultSet.getString("pace")),
                        Double.parseDouble(resultSet.getString("w_percentage"))
                );
                players.add(player);
            }
            Phoenix.closeResource(null,preparedStatement,resultSet);
        }
        return players;
    }

    @Override
    public int getRankList(Connection connection, String column, Double value) throws SQLException {
        PreparedStatement preparedStatement = null;
        int rank = 0;
        /*查询记录数*/
        ResultSet resultSet = null;
        if(connection!=null)
        {
            String sql = "";
            if (column.equals("rk")) {
                sql = "select count(1) from \"player\" where to_number(\""+column+"\") <"+value;
            } else {
                sql = "select count(1) from \"player\" where to_number(\""+column+"\") >"+value;
            }
            System.out.println(sql);
            Object[] params = {};
            resultSet = Phoenix.execute(connection,resultSet,preparedStatement,params,sql);
            while(resultSet.next())
            {
                rank = resultSet.getInt(1);
            }
            Phoenix.closeResource(null,preparedStatement,resultSet);
        }
        return rank+1;
    }
}
