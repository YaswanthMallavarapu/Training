package com.taskmanagement.controller;

import com.taskmanagement.dto.UserReqDto;
import com.taskmanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody UserReqDto userReqDto){
        userService.adduser(userReqDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();

    }

}
