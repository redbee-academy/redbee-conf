package io.redbee.product.conf.ms.talks.talks.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
@Configuration
public class DataBaseConfiguration {

    @Bean("talksTemplate")
    public NamedParameterJdbcTemplate talksTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }


}