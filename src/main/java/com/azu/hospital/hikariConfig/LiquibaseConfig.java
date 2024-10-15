//package com.azu.hospital.hikariConfig;
//
//import liquibase.integration.spring.SpringLiquibase;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class LiquibaseConfig {
//
//    @Bean
//    public SpringLiquibase liquibase(DataSource dataSource) {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
//        liquibase.setDataSource(dataSource);
//        return liquibase;
//    }
//}