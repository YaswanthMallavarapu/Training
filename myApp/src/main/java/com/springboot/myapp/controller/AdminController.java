package com.springboot.myapp.controller;

import com.springboot.myapp.dto.AdminAddDto;
import com.springboot.myapp.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;
    @PostMapping("/add")
    public ResponseEntity<?> addAdmin(@RequestBody AdminAddDto adminAdddto){

        adminService.addAdmin(adminAdddto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
