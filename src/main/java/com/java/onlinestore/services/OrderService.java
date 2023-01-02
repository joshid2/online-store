package com.java.onlinestore.services;

import com.java.onlinestore.model.Order;
import com.java.onlinestore.repository.OrderDetailsRepository;
import com.java.onlinestore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(int id){
        return orderRepository.findById(id);
    }

    public Order placeOrder(Order order){
        return orderRepository.save(order);
    }

    public void removeOrderById(int id){
        orderRepository.deleteById(id);
    }
}
