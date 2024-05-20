package com.tigrisblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class TigrisBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(TigrisBlogApplication.class, args);
    //http://localhost:8080/swagger-ui/index.html
	}

}
