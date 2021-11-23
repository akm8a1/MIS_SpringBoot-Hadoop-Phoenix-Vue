package com.liu.nba.controller;

import com.liu.nba.utils.SystemUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountsController {

    @GetMapping("/getCounts")
    public int getCounts() {
        return SystemUtils.LIST_COUNT;
    }
}
