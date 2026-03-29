package com.springboot.myapp.controller;

import com.springboot.myapp.dto.ExecutiveDto;
import com.springboot.myapp.enums.JobTitle;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.model.Executive;
import com.springboot.myapp.service.ExecutiveService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/executive")
public class ExecutiveController {
    private final ExecutiveService executiveService;

    /* ACCESS : executive */
    @PostMapping("/add")
    public ResponseEntity<?> addExecutive(@Valid @RequestBody ExecutiveDto executiveDto){
        Executive executive=executiveService.addExecutive(executiveDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(executive);

    }

    /* Access : admin */
    @GetMapping("/get-all")
    public List<Executive> getAllExecutives(@RequestParam(value = "page",required = false,defaultValue = "0")int page,
                                           @RequestParam(value = "size",required = false,defaultValue = "5") int size){
        return executiveService.getAllExecutives(page,size);
    }

    /* Access : admin */
    @GetMapping("/get/{id}")
    public Executive getExecutiveById(@PathVariable long id){
        return executiveService.getExecutiveById(id);
    }

    /* Access : admin */
    @GetMapping("/get")
    public List<Executive> filterExecutive(@RequestParam(value = "job-title",required = false)JobTitle jobTitle){
        return executiveService.filterExecutive(jobTitle);

    }
}
