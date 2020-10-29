package com.example.wsy.spring.db;

import com.example.wsy.spring.dao.MyDao;
import com.example.wsy.spring.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlTest {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("mysql-annotation.xml");
        MyService service = (MyService) applicationContext.getBean("myService");
        for (int i = 0; i < 5; i ++) {
            System.out.println(service.save(true));
        }


    }

    private static void originJdbcMethod(Connection con) {
//        String url = null;
//        String username = null;
//        String password = null;
//        String classPath = null;

        try {
//            url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
//            classPath = "com.mysql.cj.jdbc.Driver";
//            username = "root";
//            password = "root";

//            Class.forName(classPath);

            String sql = "insert into user ( name, age) values ('tom', 3)";

//            Connection con = DriverManager.getConnection(url, username, password);

            Statement statement = con.createStatement();
            statement.execute(sql);
            System.out.println(con);
            statement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
