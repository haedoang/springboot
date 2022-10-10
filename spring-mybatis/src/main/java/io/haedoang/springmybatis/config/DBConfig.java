package io.haedoang.springmybatis.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * author : haedoang
 * date : 2022/10/10
 * description :
 */
@Configuration
@MapperScan(basePackages = {"io.haedoang.springmybatis.mapper"})
public class DBConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setTypeAliasesPackage("io.haedoang.springmybatis.entity");
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(ApplicationContext context) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(context));
    }
}