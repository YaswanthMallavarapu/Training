package com.springboot.myapp.controller;

import com.springboot.myapp.dto.CustomerDto;
import com.springboot.myapp.dto.CustomerSignUpDto;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    /* Access : permits all(public) */
    @PostMapping("/api/customer/add")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDto customerDto){
        Customer customer=customerService.addCustomer(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customer);

    }

    @PostMapping("/api/customer/sign-up")
    public ResponseEntity<?> customerSignUp(@Valid @RequestBody CustomerSignUpDto customerSignUpDto){
        customerService.signUpCustomer(customerSignUpDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();

    }
}
