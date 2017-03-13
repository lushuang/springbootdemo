package com.ls.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: lushuang
 * Date: 2017/03/13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.yml"})
public class DatabaseConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));//用户名
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));//密码
        // dataSource.setInitialSize(2);
        // dataSource.setMaxActive(20);
        // dataSource.setMinIdle(0);
        // dataSource.setMaxWait(60000);
        // dataSource.setValidationQuery("SELECT 1");
        // dataSource.setTestOnBorrow(false);
        // dataSource.setTestWhileIdle(true);
        // dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.ls.persistance.entity"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"/*environment.getRequiredProperty("spring.jpa.dialect")*/);
        properties.put("hibernate.show_sql", environment.getRequiredProperty("spring.jpa.show-sql"));
        // properties.put("hibernate.format_sql", environment.getRequiredProperty("spring.jpa.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
        return properties;
    }

    // @Bean
    // @Autowired
    // public HibernateTransactionManager transactionManager(SessionFactory s) {
    //     HibernateTransactionManager txManager = new HibernateTransactionManager();
    //     txManager.setSessionFactory(s);
    //     return txManager;
    // }

}
