package com.animesh.backend.repo;

import com.animesh.backend.model.Product;
import com.animesh.backend.service.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

}
