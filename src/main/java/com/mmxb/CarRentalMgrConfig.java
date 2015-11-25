package com.mmxb;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Xing on 2015/11/23.
 */

@Configuration
public class CarRentalMgrConfig {
    @Value("${mgr.jdbc.type:mysql}")
    private String jdbcType;

    @Bean(name = "mgrDataSource")
    @ConfigurationProperties(prefix = "mgr.jdbc")
    public DataSource mgrDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mgrSqlSessionFactory")
    public SqlSessionFactory mgrSqlSessionFactory(@Qualifier("mgrDataSource") DataSource mgrDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(mgrDataSource);
        bean.setConfigLocation(new ClassPathResource("MybatisConfig_" + jdbcType + ".xml"));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("mgrDataSource") DataSource mgrDataSource) throws Exception {
        return new DataSourceTransactionManager(mgrDataSource);
    }
}
