package com.masirhh.ucmssystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.masirhh.ucmssystem.mapper")
@EnableDiscoveryClient
public class UcmsSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcmsSystemApplication.class, args);
    }

}
