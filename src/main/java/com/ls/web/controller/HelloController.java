package com.ls.web.controller;

import com.ls.persistance.dao.DataSourceDao;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: lushuang
 * Date: 2017/03/13
 * Time: 22:18
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class HelloController {

    @Autowired
    private DataSourceDao dataSourceDao;

    @GetMapping(value = "hello")
    public String hello() {
        DataSource ds = dataSourceDao.getDataSource();
        try {
            System.out.println(ds.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Session session = dataSourceDao.getCurrentSession();
        System.out.println(session);

        Session newSession = dataSourceDao.getNewSession();
        System.out.println(newSession);
        return "hello";
    }
}
