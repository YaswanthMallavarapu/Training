package com.springboot.myapp.controller;

import com.springboot.myapp.dto.DocumentResDto;
import com.springboot.myapp.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/document")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<Object> upload(Principal principal,
                                         @RequestParam("file")MultipartFile file) throws IOException {

        DocumentResDto documentResDto=documentService.upload(principal.getName(),file);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(documentResDto);

    }
}
