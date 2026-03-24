package com.springboot.myapp.mapper;

import com.springboot.myapp.dto.CustomerDto;
import com.springboot.myapp.model.Customer;

public class CustomerMapper {
    public static Customer mapToCustomer(CustomerDto customerDto){
        Customer customer=new Customer();
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setCity(customerDto.city());
        return customer;
    }
}
