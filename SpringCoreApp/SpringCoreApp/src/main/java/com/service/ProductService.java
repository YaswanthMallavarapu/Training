package com.service;

import com.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public String getPrint() {
        return productRepository.printStmt();
    }
}
