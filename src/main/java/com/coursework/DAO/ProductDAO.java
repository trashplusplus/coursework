package com.coursework.DAO;

import com.coursework.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Long> {
    Product getProductByTitle(String title);
    Product getProductById(Long id);
    List<Product> findAll();
}
