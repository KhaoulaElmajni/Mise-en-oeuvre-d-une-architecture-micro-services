package me.elmajni.customerservicevf.repositories;


import me.elmajni.customerservicevf.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
