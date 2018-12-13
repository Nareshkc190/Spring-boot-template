package com.example.ecommerce.repository;

import com.example.ecommerce.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersRepository  extends JpaRepository<Orders, Long> {
}
