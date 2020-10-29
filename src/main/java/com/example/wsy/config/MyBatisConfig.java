package com.example.wsy.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.wsy.dao")
public class MyBatisConfig {
}