package com.coursework.services;

import com.coursework.DAO.OrderProductDAO;
import com.coursework.entity.Order;
import com.coursework.entity.OrderProduct;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderProductServiceImpl implements OrderProductService{

    private final OrderProductDAO orderProductDAO;

    public OrderProductServiceImpl(OrderProductDAO orderProductDAO){
        this.orderProductDAO = orderProductDAO;
    }

    @Override
    public void save(OrderProduct orderProduct) {
        orderProductDAO.save(orderProduct);
    }

    @Override
    public void delete(OrderProduct orderProduct) {
        orderProductDAO.delete(orderProduct);
    }

    @Override
    public List<OrderProduct> findAll() {
        return orderProductDAO.findAll();
    }

    @Override
    public List<OrderProduct> getAllByOrderId(long orderId){
        return orderProductDAO.getAllByOrderId(orderId);
    }

    @Override
    public OrderProduct getByOrderId(Long orderId) {
        return orderProductDAO.getById(orderId);
    }
}
