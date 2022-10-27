package me.elmajni.customerservicevf;

import me.elmajni.customerservicevf.dto.CustomerRequestDTO;
import me.elmajni.customerservicevf.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceVfApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceVfApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerService customerService){
		return args -> {
			customerService.save(new CustomerRequestDTO("001","Adria","med@adria.com"));
			customerService.save(new CustomerRequestDTO("002","LinkedIn","linked@adria.com"));
		};
	}
}
