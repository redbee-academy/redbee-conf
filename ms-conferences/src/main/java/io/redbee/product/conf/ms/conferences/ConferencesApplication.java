package io.redbee.product.conf.ms.conferences;

import io.redbee.product.conf.ms.conferences.configuration.DatabaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConferencesApplication {
	@Autowired
	DatabaseConfiguration databaseConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(ConferencesApplication.class, args);
	}

}
