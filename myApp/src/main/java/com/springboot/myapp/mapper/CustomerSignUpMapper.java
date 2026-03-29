package com.springboot.myapp.mapper;

import com.springboot.myapp.dto.CustomerSignUpDto;
import com.springboot.myapp.model.Customer;

public class CustomerSignUpMapper {
    public static Customer mapToEntity(CustomerSignUpDto customerSignUpDto){
        Customer customer=new Customer();
        customer.setName(customerSignUpDto.name());
        customer.setEmail(customerSignUpDto.email());
        customer.setCity(customerSignUpDto.city());
        return customer;
    }
}
