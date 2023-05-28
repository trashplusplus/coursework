package com.coursework.services;

import com.coursework.DAO.ProductDAO;
import com.coursework.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public void save(Product product) {
        productDAO.save(product);
    }

    @Override
    public void delete(Product product) {
        productDAO.delete(product);
    }

    @Override
    public Product getById(long id) {
        return productDAO.getProductById(id);
    }

    @Override
    public Product findByTitle(String title) {
        return productDAO.getProductByTitle(title);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }
}
