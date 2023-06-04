package com.coursework.services;

import com.coursework.entity.Order;
import com.coursework.entity.OrderProduct;

import java.util.List;

public interface OrderProductService {

    void save(OrderProduct orderProduct);
    void delete(OrderProduct orderProduct);

    List<OrderProduct> findAll();
    List<OrderProduct> getAllByOrderId(long id);

    OrderProduct getByOrderId(Long orderId);


}
