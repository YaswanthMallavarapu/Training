package com.springboot.myapp.service;

import com.springboot.myapp.dto.CustomerDto;
import com.springboot.myapp.exceptions.ResourceNotFoundException;
import com.springboot.myapp.mapper.CustomerMapper;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer addCustomer(@Valid CustomerDto customerDto) {
        //map this to customer
        Customer customer= CustomerMapper.mapToCustomer(customerDto);
        //save in db
        return customerRepository.save(customer);
    }

    public Customer getById(long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Customer Id."));
    }
}
