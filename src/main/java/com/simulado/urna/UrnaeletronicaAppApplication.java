package com.simulado.urna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

//@EnableSwagger2
//@EnableSwagger2WebMvc
@SpringBootApplication
@EnableWebMvc
public class UrnaeletronicaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrnaeletronicaAppApplication.class, args);
	}

}
