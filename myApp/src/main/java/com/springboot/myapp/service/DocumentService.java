package com.springboot.myapp.service;


import com.springboot.myapp.dto.DocumentResDto;
import com.springboot.myapp.exceptions.InvalidFileFormatException;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.model.Document;
import com.springboot.myapp.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final CustomerService customerService;
    private final static String UPLOAD_PATH="C:/Users/harip/Desktop/training/trs-UI/public/uploads/";

    public DocumentResDto upload(String name, MultipartFile file) throws IOException {

        Customer customer=customerService.getByUsername(name);

        File directory=new File(UPLOAD_PATH);

        String fileName=file.getOriginalFilename();
        String extension= Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];


        if(!extension.equalsIgnoreCase("PNG") &&
                !extension.equalsIgnoreCase("JPG") &&
                !extension.equalsIgnoreCase("JPEG"))
            throw new InvalidFileFormatException("File Extension not supported.");

        Path path= Paths.get(UPLOAD_PATH+fileName);
        Files.write(path,file.getBytes());



        Document document=new Document();
        document.setCustomer(customer);
        document.setProfileImage(fileName);
        Document saved=documentRepository.save(document);

        return new DocumentResDto(
                saved.getId(),
                saved.getProfileImage(),
                saved.getCustomer().getId()
        );



    }
}
