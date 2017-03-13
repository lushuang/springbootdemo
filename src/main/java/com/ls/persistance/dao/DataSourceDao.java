package com.ls.persistance.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: lushuang
 * Date: 2017/03/13
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class DataSourceDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SessionFactory sessionFactory;

    public DataSource getDataSource() {
        try {
            System.out.println(dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.dataSource;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Session getNewSession() {
        return sessionFactory.openSession();
    }
}
