package com.springboot.myapp.mapper;

import com.springboot.myapp.dto.ExecutiveDto;
import com.springboot.myapp.model.Executive;

public class ExecutiveMapper {
    public static Executive mapToExecutive(ExecutiveDto executiveDto){
        Executive executive=new Executive();
        executive.setName(executiveDto.name());
        executive.setJobTitle(executiveDto.jobTitle());
        return executive;
    }
}
