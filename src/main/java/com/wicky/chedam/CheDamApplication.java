package com.wicky.chedam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.wicky.chedam")
public class CheDamApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheDamApplication.class, args);
	}

}


