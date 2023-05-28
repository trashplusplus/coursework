package com.coursework.services;

import com.coursework.MyUser;
import com.coursework.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);
    void delete(Product product);

    Product getById(long id);
    Product findByTitle(String title);

    List<Product> findAll();
}
