package com.springboot.myapp.mapper;

import com.springboot.myapp.dto.CustomerDto;
import com.springboot.myapp.dto.CustomerResponseDto;
import com.springboot.myapp.model.Customer;

public class CustomerMapper {
    public static Customer mapToCustomer(CustomerDto customerDto){
        Customer customer=new Customer();
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setCity(customerDto.city());
        return customer;
    }

    public static CustomerResponseDto mapEntityToDto(Customer customer){
        return new CustomerResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCity()
        );
    }
}
