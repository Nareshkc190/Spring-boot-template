package com.example.ecommerce.controller;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.Orders;
import com.example.ecommerce.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrdersController {

    @Autowired
    OrdersRepository ordersRepository;

    @GetMapping("/orders")
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @PostMapping("/orders")
    public  Orders createOrder(@Valid @RequestBody  Orders order) {
        return ordersRepository.save(order);
    }

    @GetMapping("/orders/{id}")
    public  Orders getOrderById(@PathVariable(value = "id") Long orderId) {
        return ordersRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Orders", "id", orderId));
    }


    @PutMapping("/orders/{id}")
    public  Orders updateOrder(@PathVariable(value = "id") Long orderId,
                                   @Valid @RequestBody  Orders orderDetails) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Orders", "id", orderId));

        order.setShipped(orderDetails.getShipped());
        order.setCustomerName(orderDetails.getCustomerName());
        order.setAddress(orderDetails.getAddress());
        order.setQuantity(orderDetails.getQuantity());
        order.setId(orderDetails.getId());


        Orders updatedOrder = ordersRepository.save(order);
        return updatedOrder;
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long orderId) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Orders", "id", orderId));
        ordersRepository.delete(order);
        return ResponseEntity.ok().build();
    }


}