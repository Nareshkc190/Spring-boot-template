package com.example.ecommerce.controller;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.Products;
import com.example.ecommerce.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
 
@RestController
@RequestMapping("/api")
public class ProductsController {

    @Autowired
    ProductsRepository productsRepository;

    @GetMapping("/products")
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    @PostMapping("/products")
    public  Products createProduct(@Valid @RequestBody  Products product) {
        return productsRepository.save(product);
    }

    @GetMapping("/products/{id}")
    public  Products getProductById(@PathVariable(value = "id") Long productId) {
        return productsRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Products", "id", productId));
    }


    @PutMapping("/products/{id}")
    public  Products updateProduct(@PathVariable(value = "id") Long productId,
                           @Valid @RequestBody  Products productDetails) {
         Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Products", "id", productId));

        product.setName(productDetails.getName());
        product.setBrand(productDetails.getBrand());
        product.setColor(productDetails.getColor());
        product.setWeight(productDetails.getWeight());
        product.setPrice(productDetails.getPrice());
        product.setId(productDetails.getId());



        product.setSerialNumber(productDetails.getSerialNumber());

        Products updatedProduct = productsRepository.save(product);
        return updatedProduct;
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) {
         Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Products", "id", productId));
        productsRepository.delete(product);
        return ResponseEntity.ok().build();
    }


}