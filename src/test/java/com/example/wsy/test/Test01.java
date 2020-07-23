package com.example.wsy.test;

import com.example.wsy.dto.User;
import com.example.wsy.service.TestService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class Test01 {

    @Autowired
    private TestService test;

    @Autowired
    DataSource dataSource;

//    @Autowired
//    TestRestTemplate template;

    @Test
    public void test01() throws SQLException {
        String id = "1";
//        System.out.println(template);
        User user = test.test01(id);
        System.out.println(user);
//        System.out.println(dataSource.getConnection());
    }

}
