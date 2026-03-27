package com.springboot.myapp.mapper;

import com.springboot.myapp.dto.CustomerPlanResDto;
import com.springboot.myapp.model.CustomerPlan;

public class CustomerPlanMapper {
    public static CustomerPlanResDto mapToDto(CustomerPlan customerPlan){
        return new CustomerPlanResDto(
                customerPlan.getCustomer().getId(),
                customerPlan.getCustomer().getName(),
                customerPlan.getCustomer().getEmail(),
                customerPlan.getCustomer().getCity(),
                customerPlan.getStartDate(),
                customerPlan.getEndDate(),
                customerPlan.getPlan().getPlanName()
        );

    }
}
