package me.elmajni.billingservicevf.repositories;

import me.elmajni.billingservicevf.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@RepositoryRestResource
public interface InvoiceRespository extends JpaRepository<Invoice,String> {
    List<Invoice> findByCustomerId(String customerId);
}
