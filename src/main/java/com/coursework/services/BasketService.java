package com.coursework.services;

import com.coursework.entity.BasketProduct;

import java.util.List;

public interface BasketService {

    void save(BasketProduct basketProduct);
    void delete(BasketProduct basketProduct);

   // BasketProduct getById(long id);
    //BasketProduct findByUserId(long userId);

    List<BasketProduct> findAll();

    void deleteProductByUserIdAndProductId(Long userId, Long productId);
    BasketProduct getByProductId(Long productId);

    List<BasketProduct> getAllByUserId(Long userId);

}
