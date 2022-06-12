package com.hawk.example.transactional;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author hawk
 * @package com.hawk.example.transactional
 * @desc
 * @date 2022/1/19
 */
@Configuration
public class MybatisPlusConfig {
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource initDataSource() {
        return DataSourceBuilder.create().build();
    }
}
