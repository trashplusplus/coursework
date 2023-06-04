package com.coursework.services;

import com.coursework.entity.BasketProduct;
import com.coursework.entity.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {

    void save(Order order);
    void delete(Order order);

    List<Order> findAll();

    Order getByOrderId(Long orderId);
    List<Order> getOrdersByUserId(Long id);


}
