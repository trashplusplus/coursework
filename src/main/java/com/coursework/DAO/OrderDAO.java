package com.coursework.DAO;

import com.coursework.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {
    Order getOrderById(Long id);
    List<Order> getOrdersByUserId(Long id);
}
