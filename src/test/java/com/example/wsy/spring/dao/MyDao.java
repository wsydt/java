package com.example.wsy.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MyDao {

    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    public boolean save (boolean flag) {

        String sql = "insert into user ( name, age) values ('tom', 3)";
        flag = jdbcTemplate.update(sql) > 0;
        return flag;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(){
        String sql = "insert into user ( name, age) values ('jerry', 18)";
        jdbcTemplate.update(sql);

    }
}
