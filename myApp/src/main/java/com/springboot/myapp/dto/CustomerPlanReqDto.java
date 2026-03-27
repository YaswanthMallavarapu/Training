package com.springboot.myapp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CustomerPlanReqDto (
        String coupon,
        LocalDate startDate,
        BigDecimal discount
){
}
