package me.elmajni.billingservicevf;

import me.elmajni.billingservicevf.dtos.InvoiceRequestDTO;
import me.elmajni.billingservicevf.exception.CustomerNotFoundException;
import me.elmajni.billingservicevf.feign.CustomerRestClient;
import me.elmajni.billingservicevf.models.Customer;
import me.elmajni.billingservicevf.repositories.InvoiceRespository;
import me.elmajni.billingservicevf.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceVfApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceVfApplication.class, args);
    }

    @Bean
    CommandLineRunner start(InvoiceService invoiceService) {
        return args -> {
            try {
                invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(527550),"001"));
                invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(200000),"001"));
                invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(550),"002"));
            } catch (CustomerNotFoundException e) {
                throw new RuntimeException(e);
            }

        };
    }
}
