package com.springboot.myapp.service;

import com.springboot.myapp.dto.ExecutiveDto;
import com.springboot.myapp.mapper.ExecutiveMapper;
import com.springboot.myapp.model.Executive;
import com.springboot.myapp.repository.ExecutiveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExecutiveService {
    private final ExecutiveRepository executiveRepository;

    public Executive addExecutive(ExecutiveDto executiveDto) {
        //map executiveDto to Executive
        Executive executive=ExecutiveMapper.mapToExecutive(executiveDto);
        //save in database
        return executiveRepository.save(executive);

    }
}
