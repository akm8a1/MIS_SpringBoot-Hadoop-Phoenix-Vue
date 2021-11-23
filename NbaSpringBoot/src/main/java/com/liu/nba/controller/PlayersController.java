package com.liu.nba.controller;

import com.liu.nba.pojo.Player;
import com.liu.nba.service.PlayerService;
import com.liu.nba.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayersController {
    @Autowired
    private PlayerService service;
    @GetMapping("/players/{startPage}/{pageSize}")
    public List<Player> getAllPlayers(@PathVariable int startPage, @PathVariable int pageSize) throws IOException, SQLException {
        return service.getAllPlayers(startPage,pageSize);
    }
    @GetMapping("/playerscount")
    public int getCounts() throws SQLException {
        return service.getPlayersCount();
    }
    @GetMapping("/getPlayerByName/{name}/{startPage}/{pageSize}")
    public List<Player> getPlayersByName(@PathVariable String name,@PathVariable int startPage, @PathVariable int pageSize) throws SQLException {
        List<Player> list = service.getPlayersLike(name,startPage,pageSize);
        SystemUtils.LIST_COUNT = list.size();
        return list;
    }
    @GetMapping("/getMax/{columns}")
    public List<Player> getMaxPlayers(@PathVariable String columns) throws SQLException {
        List<Player> list = service.getMaxPlayer(columns);
        return list;
    }
    @GetMapping("/getMaxs")
    public List<Player> getAllMax() throws SQLException {
        List<Player> list = new ArrayList<>();
        list.add(service.getMaxPlayer("age").get(0));
        list.add(service.getMaxPlayer("salary_millions").get(0));
        list.add(service.getMaxPlayer("points").get(0));
        list.add(service.getMaxPlayer("mp").get(0));
        list.add(service.getMaxPlayer("three").get(0));
        list.add(service.getMaxPlayer("three_percent").get(0));
        list.add(service.getMaxPlayer("efg").get(0));
        list.add(service.getMaxPlayer("ft").get(0));
        list.add(service.getMaxPlayer("trb").get(0));
        list.add(service.getMaxPlayer("ast").get(0));
        list.add(service.getMaxPlayer("stl").get(0));
        list.add(service.getMaxPlayer("blk").get(0));
        return list;
    }
    @PostMapping("/getRk")
    public List<Integer> getRk(@RequestBody Player player) throws SQLException {
        System.out.println(player.getPlayer());
        List<Integer> list = new ArrayList<>();
        list.add(service.getRankList("rk", (double) player.getRk()));
        list.add(service.getRankList("mp", player.getMp()));
        list.add(service.getRankList("fg", player.getFg()));
        list.add(service.getRankList("fga", player.getFga()));
        list.add(service.getRankList("three", player.getThree()));
        list.add(service.getRankList("efg", player.getThree()));
        list.add(service.getRankList("ft", player.getThree()));
        list.add(service.getRankList("trb", player.getTrb()));
        list.add(service.getRankList("ast", player.getAst()));
        list.add(service.getRankList("stl", player.getStl()));
        list.add(service.getRankList("blk", player.getBlk()));
        list.add(service.getRankList("tov", player.getTov()));
        list.add(service.getRankList("pf", player.getPf()));
        list.add(service.getRankList("points", player.getPoints()));
        list.add(service.getRankList("rpm", player.getRpm()));
        list.add(service.getRankList("wins_rpm", player.getWins_rpm()));
        list.add(service.getRankList("pie", player.getPie()));
        return list;
    }

}
