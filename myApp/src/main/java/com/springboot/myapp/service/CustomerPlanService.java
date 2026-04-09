package com.springboot.myapp.service;

import com.springboot.myapp.dto.CustomerPlanReqDto;
import com.springboot.myapp.dto.CustomerPlanResDto;
import com.springboot.myapp.mapper.CustomerPlanMapper;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.model.CustomerPlan;
import com.springboot.myapp.model.Plan;
import com.springboot.myapp.repository.CustomerPlanRepository;
import com.springboot.myapp.utility.EndDateUtility;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerPlanService {
    private final CustomerPlanRepository customerPlanRepository;
    private final CustomerService customerService;
    private final PlanService planService;
    private final EndDateUtility endDateUtility;

    public void assignPlan(CustomerPlanReqDto customerPlanReqDto, long customerId, long planId) {
        //check for customer
        Customer customer=customerService.getById(customerId);
        //check for plan
        Plan plan=planService.getById(planId);
        //add other details for customer plan
        //create end date from start date
        LocalDate endDate=endDateUtility.getEndDate(customerPlanReqDto.startDate(),plan.getDays());
        CustomerPlan customerPlan=new CustomerPlan();

        customerPlan.setStartDate(customerPlanReqDto.startDate());
        customerPlan.setEndDate(endDate);
        customerPlan.setDiscount(customerPlanReqDto.discount());
        customerPlan.setCoupon(customerPlanReqDto.coupon());
        customerPlan.setCustomer(customer);
        customerPlan.setPlan(plan);

        //save in DB
        customerPlanRepository.save(customerPlan);
    }

    public List<CustomerPlanResDto> getAllCustomerDetails(int page,int size) {
        //create pagination
        Pageable pageable=PageRequest.of(page,size);
        //get all customers with plans
        Page<CustomerPlan> list=customerPlanRepository.findAll(pageable);
        //do filtering and result
        return list.
                toList()
                .stream()
                .map(CustomerPlanMapper::mapToDto)
                .toList();

    }

    public void buyPlan(CustomerPlanReqDto customerPlanReqDto, String username, long planId) {
        //check for customer
        Customer customer=customerService.getByUsername(username);
        //check for plan
        Plan plan=planService.getById(planId);
        //add other details for customer plan
        //create end date from start date
        LocalDate endDate=endDateUtility.getEndDate(customerPlanReqDto.startDate(),plan.getDays());
        CustomerPlan customerPlan=new CustomerPlan();

        customerPlan.setStartDate(customerPlanReqDto.startDate());
        customerPlan.setEndDate(endDate);
        customerPlan.setDiscount(customerPlanReqDto.discount());
        customerPlan.setCoupon(customerPlanReqDto.coupon());
        customerPlan.setCustomer(customer);
        customerPlan.setPlan(plan);

        //save in DB
        customerPlanRepository.save(customerPlan);
    }
}
