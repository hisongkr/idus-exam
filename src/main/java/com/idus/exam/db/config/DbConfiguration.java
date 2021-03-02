package com.idus.exam.db.config;



import com.idus.exam.db.base.SqlSessionTemplateDatabaseFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DbConfiguration {
    @Autowired
    private ApplicationContext applicationContext;

    // exam write db
    @Bean(name = "examWriteDbConfig")
    @ConfigurationProperties(prefix = "examwrite")
    public HikariConfig examWriteDbConfig() {
        return new HikariConfig();
    }

    @Primary
    @Bean(name = "examWriteDataSource", destroyMethod = "close")
    public HikariDataSource examWriteDataSource() {
        HikariDataSource dataSource = new HikariDataSource(examWriteDbConfig());
        return dataSource;
    }

    @Bean(name = "examWriteSqlSessionFactory")
    public SqlSessionFactory examWriteSqlSessionFactory(@Autowired @Qualifier("examWriteDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/sqlmap/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "examWriteSqlSessionTemplate", destroyMethod = "clearCache")
    public SqlSessionTemplate examWriteSqlSessionTemplate(@Autowired @Qualifier("examWriteSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    // exam read db
    @Bean(name = "examReadDbConfig")
    @ConfigurationProperties(prefix = "examread")
    public HikariConfig examReadDbConfig() {
        return new HikariConfig();
    }

    @Bean(name = "examReadDataSource", destroyMethod = "close")
    public HikariDataSource examReadDataSource() {
        HikariDataSource dataSource = new HikariDataSource(examReadDbConfig());
        return dataSource;
    }

    @Bean(name = "examReadSqlSessionFactory")
    public SqlSessionFactory examReadSqlSessionFactory(@Autowired @Qualifier("examReadDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/sqlmap/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "examReadSqlSessionTemplate")
    public SqlSessionTemplate examReadSqlSessionTemplate(@Autowired @Qualifier("examReadSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    ///////
    @Bean
    public ServiceLocatorFactoryBean serviceLocatorFactoryBean() {
        ServiceLocatorFactoryBean serviceLocatorFactoryBean = new ServiceLocatorFactoryBean();
        serviceLocatorFactoryBean.setServiceLocatorInterface(SqlSessionTemplateDatabaseFactory.class);
        return serviceLocatorFactoryBean;
    }
}
