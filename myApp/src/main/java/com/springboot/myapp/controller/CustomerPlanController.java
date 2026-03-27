package com.springboot.myapp.controller;

import com.springboot.myapp.dto.CustomerPlanReqDto;
import com.springboot.myapp.dto.CustomerPlanResDto;
import com.springboot.myapp.service.CustomerPlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer/plan")
public class CustomerPlanController {

    private final CustomerPlanService customerPlanService;
    @PostMapping("/add/{customerId}/{planId}")
    public ResponseEntity<?> buyPlan(@RequestBody CustomerPlanReqDto customerPlanReqDto,
                                          @PathVariable  long customerId,
                                          @PathVariable long planId){
        customerPlanService.buyPlan(customerPlanReqDto,customerId,planId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCustomerDetails(@RequestParam(value = "page",required = false,defaultValue = "0")int page,
                                                                          @RequestParam(value = "size",required = false,defaultValue = "5")int size){

        List<CustomerPlanResDto>list=customerPlanService.getAllCustomerDetails(page,size);
        return ResponseEntity
                .status(HttpStatus.OK)
                        .body(list);

    }

    

}
