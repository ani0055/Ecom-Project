package com.animesh.backend.controller;

import com.animesh.backend.model.Product;
import com.animesh.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin // helps us connect to the frontend cause frontend is running on port 5173 and backend on 8080 so we get a cros error
@RequestMapping("/api")
public class ProductController {

    private final ProductService service;

    @Autowired //Constructor Injection best practice
    public ProductController(ProductService service){
        this.service = service;
    }

    @RequestMapping("/")
    public String greet(){
        return "Hola Amigos";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }
}
