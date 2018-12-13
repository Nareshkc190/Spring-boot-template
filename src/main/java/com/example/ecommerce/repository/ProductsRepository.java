package com.example.ecommerce.repository;

import com.example.ecommerce.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductsRepository  extends JpaRepository<Products, Long> {
}
