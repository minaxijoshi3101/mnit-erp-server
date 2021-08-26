package com.mnit.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

@SpringBootApplication
public class ErpApplication {
	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}

}
