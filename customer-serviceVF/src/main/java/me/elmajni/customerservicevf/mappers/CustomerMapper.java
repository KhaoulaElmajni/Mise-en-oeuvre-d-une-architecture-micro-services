package me.elmajni.customerservicevf.mappers;


import me.elmajni.customerservicevf.dto.CustomerRequestDTO;
import me.elmajni.customerservicevf.dto.CustomerResponseDTO;
import me.elmajni.customerservicevf.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO customerToCustomerDTO(Customer customer);
    Customer customerRequestDtoToCustomer(CustomerRequestDTO customerRequestDTO);
}
