package me.elmajni.customerservicevf.services;


import me.elmajni.customerservicevf.dto.CustomerRequestDTO;
import me.elmajni.customerservicevf.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO getCustomer(String id);
    List<CustomerResponseDTO> getCustomers();
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);
    void deleteCustomer(String id);
}
