package com.azu.hospital;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication(scanBasePackages = "com.azu.hospital", exclude = LiquibaseAutoConfiguration.class)
@EnableScheduling
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Hospital {

	public static void main(String[] args) {
    SpringApplication.run(Hospital.class, args);


	}
}
