package com.springboot.myapp.service;

import com.springboot.myapp.dto.CustomerDto;
import com.springboot.myapp.dto.CustomerSignUpDto;
import com.springboot.myapp.enums.Role;
import com.springboot.myapp.exceptions.ResourceNotFoundException;
import com.springboot.myapp.mapper.CustomerMapper;
import com.springboot.myapp.mapper.CustomerSignUpMapper;
import com.springboot.myapp.mapper.UserMapper;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.model.User;
import com.springboot.myapp.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
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

    public void signUpCustomer(CustomerSignUpDto customerSignUpDto) {
        //map customerdto to customer
        Customer customer= CustomerSignUpMapper.mapToEntity(customerSignUpDto);
        //map customer dto to user
        User user= UserMapper.mapToEntity(customerSignUpDto);
        user.setRole(Role.CUSTOMER);
        user.setPassword(passwordEncoder.encode(customerSignUpDto.password()));
        //save user in DB

        user=userService.insertUser(user);

        //add additional details to customer
        customer.setUser(user);
        //save customer in DB
        customerRepository.save(customer);
    }

    public Customer getByUsername(String username) {
        return customerRepository.getCustomerByUsername(username);
    }

    public Customer getByCustomer(String name) {
        return customerRepository.getCustomerByUsername(name);
    }
}
