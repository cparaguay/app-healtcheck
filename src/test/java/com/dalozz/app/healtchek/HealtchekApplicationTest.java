package com.dalozz.app.healtchek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.dalozz.app.healtchek.config.ServiceApplication;
import com.dalozz.app.healtchek.config.ServletApplication;

@Configuration
@EnableAutoConfiguration
@Import({ServletApplication.class, ServiceApplication.class})
public class HealtchekApplicationTest {

	public static void main(String[] args) {
		SpringApplication.run(HealtchekApplication.class, args);
	}

}
