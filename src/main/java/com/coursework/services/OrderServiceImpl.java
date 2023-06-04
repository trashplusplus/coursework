package com.coursework.services;

import com.coursework.DAO.OrderDAO;
import com.coursework.entity.BasketProduct;
import com.coursework.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }

    @Override
    public void save(Order order) {
        orderDAO.save(order);
    }

    @Override
    public void delete(Order order) {
        orderDAO.delete(order);
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public Order getByOrderId(Long orderId) {
        return orderDAO.getById(orderId);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderDAO.getOrdersByUserId(userId);
    }


}
