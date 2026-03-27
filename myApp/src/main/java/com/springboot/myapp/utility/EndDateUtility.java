package com.springboot.myapp.utility;

import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class EndDateUtility {
    public LocalDate getEndDate(LocalDate startDate,int daysToAdd){
        return startDate.plusDays(daysToAdd);
    }
}
