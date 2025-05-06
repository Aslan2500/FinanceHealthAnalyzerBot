package com.analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class FinanceHealthAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceHealthAnalyzerApplication.class, args);
	}

}
