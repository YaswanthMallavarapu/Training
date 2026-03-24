package com.springboot.myapp.controller;

import com.springboot.myapp.dto.ExecutiveDto;
import com.springboot.myapp.model.Executive;
import com.springboot.myapp.service.ExecutiveService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ExecutiveController {
    private final ExecutiveService executiveService;

    @PostMapping("/api/executive/add")
    public ResponseEntity<?> addExecutive(@Valid @RequestBody ExecutiveDto executiveDto){
        Executive executive=executiveService.addExecutive(executiveDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(executive);

    }

}
