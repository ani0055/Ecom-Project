package com.animesh.backend.controller;

import com.animesh.backend.model.Product;
import com.animesh.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){ //using responseEntity helps us get the http  status
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){

        Product product = service.getProductById(id);

        if(product != null) return new ResponseEntity<>(product, HttpStatus.OK);

        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){

        try{
            Product product1 =  service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]>getImageProductId(@PathVariable int id){

    Product product = service.getProductById(id);
    byte[] imageFile = product.getImageData();

    return ResponseEntity.ok()
            .contentType(MediaType.valueOf(product.getImageType()))
            .body(imageFile);
    }
}
