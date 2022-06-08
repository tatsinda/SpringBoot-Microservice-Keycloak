package com.example.demo;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Demo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}
	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			productRepository.save(new Product(null,"Computer",7600,34));
			productRepository.save(new Product(null,"Printer",1600,134));
			productRepository.save(new Product(null,"Smartphone",1600,34));
		};
	}
}
