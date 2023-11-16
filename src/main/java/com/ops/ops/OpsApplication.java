package com.ops.ops;

import com.ops.ops.persistence.repositories.OrderRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpsApplication.class, args);
	}
}
