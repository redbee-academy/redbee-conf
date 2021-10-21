package io.redbee.product.conf.ms.conferences.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
    @Configuration
    public class DatabaseConfiguration {

        @Bean("conferencesTemplate")
        public NamedParameterJdbcTemplate conferencesTemplate(DataSource dataSource) {
            return new NamedParameterJdbcTemplate(dataSource);
        }
        //comment

    }

