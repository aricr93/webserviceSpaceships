package com.pruebatecnica.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import com.pruebatecnica.demo.service.SpaceshipService;

@EnableCaching
@SpringBootApplication
public class SpaceshipsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpaceshipsApplication.class, args);

        SpaceshipService spaceshipService = context.getBean(SpaceshipService.class);
        spaceshipService.fetchSpaceshipsFromSwapi();
	}
}
