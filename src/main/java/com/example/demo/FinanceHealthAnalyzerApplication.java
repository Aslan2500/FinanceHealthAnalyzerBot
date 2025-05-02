package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//TODO: не забыть включать конфиг обратно
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FinanceHealthAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceHealthAnalyzerApplication.class, args);
	}

}
