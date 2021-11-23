package com.liu.nba;

import com.liu.nba.hadoop.HBaseUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class NbaSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(NbaSpringBootApplication.class, args);
    }
}
