package com.coursework.DAO;

import com.coursework.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductDAO extends JpaRepository<OrderProduct, Long> {

    List<OrderProduct> getAllByOrderId(Long id);
}
