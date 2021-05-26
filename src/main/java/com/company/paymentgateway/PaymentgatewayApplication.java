package com.company.paymentgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentgatewayApplication.class, args);
		System.out.println("Success! Project is running at http://localhost:8080/");
	}

}
