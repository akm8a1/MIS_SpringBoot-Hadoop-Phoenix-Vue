package com.liu.nba.controller;

import com.liu.nba.pojo.Team;
import com.liu.nba.service.TeamService;
import com.liu.nba.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class TeamController {
    @Autowired
    private TeamService service;
    @RequestMapping("/teams/{startPage}/{pageSize}")
    public List<Team> getTeams(@PathVariable int startPage, @PathVariable int pageSize) throws SQLException, IOException {
        return service.getAlTeames(startPage,pageSize);
    }
    @GetMapping("/teamscount")
    public int getCounts() throws SQLException {
        return service.getTeamsCount();
    }
    @GetMapping("/getByTeamName/{name}/{startPage}/{pageSize}")
    public List<Team> getTeamByName(@PathVariable String name,@PathVariable int startPage, @PathVariable int pageSize) throws SQLException {
        List<Team> list = service.getTeamsByName(name,startPage,pageSize);
        SystemUtils.LIST_COUNT = list.size();
        System.out.println(SystemUtils.LIST_COUNT);
        return list;
    }
}
