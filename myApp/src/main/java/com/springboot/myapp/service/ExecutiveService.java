package com.springboot.myapp.service;

import com.springboot.myapp.dto.ExecutiveDto;
import com.springboot.myapp.enums.JobTitle;
import com.springboot.myapp.exceptions.ResourceNotFoundException;
import com.springboot.myapp.mapper.ExecutiveMapper;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.model.Executive;
import com.springboot.myapp.repository.ExecutiveRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Executive> getAllExecutives(int page, int size) {
        org.springframework.data.domain.Pageable pageable =PageRequest.of(page,size);
        Page<Executive> list=executiveRepository.findAll(pageable);
        return list.toList();
    }

    public Executive getExecutiveById(long id) {
        return executiveRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Id ....."));
    }

    public List<Executive> filterExecutive(JobTitle jobTitle) {
        if(jobTitle==null){
            return List.of();
        }

        return executiveRepository.findByJobTitle(jobTitle);
    }
}
