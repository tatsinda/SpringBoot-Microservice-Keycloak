package com.example.demo;

import com.example.demo.entities.Supplier;
import com.example.demo.repositories.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;


@SpringBootApplication
public class Demo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}
	@Bean
	CommandLineRunner lineRunner(SupplierRepository supplierRepository, RepositoryRestConfiguration restConfiguration){
		return args -> {
			restConfiguration.exposeIdsFor(Supplier.class);
			Stream.of("JBOSS","ORACLE","IBM").forEach(n->{
				supplierRepository.save(new Supplier(null,n,n+"@"+n.toLowerCase()+".com"));
			});
		};
	}
}
