package io.redbee.product.conf.ms.conferences.configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
    @Configuration
    public class DatabaseConfiguration {

        @Bean("conferencesDataSource")
        public DataSource conferencesDataSource(
                @Value("${datasource.conferences.driver}") String driverClassName,
                @Value("${datasource.conferences.password}") String password,
                @Value("${datasource.conferences.username}") String username,
                @Value("${datasource.conferences.url}") String url
        ) {
            DataSourceBuilder<?> builder = DataSourceBuilder.create();
            builder.driverClassName(driverClassName);
            builder.password(password);
            builder.username(username);
            builder.url(url);

            return builder.build();
        }
        @Primary
        @Bean("conferencesTemplate")
        public NamedParameterJdbcTemplate conferencesTemplate(@Qualifier("conferencesDataSource") DataSource dataSource) {
            return new NamedParameterJdbcTemplate(dataSource);
        }

    }

