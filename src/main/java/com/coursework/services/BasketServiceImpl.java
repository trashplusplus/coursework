package com.coursework.services;

import com.coursework.entity.BasketProduct;
import com.coursework.DAO.BasketDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements BasketService{

    private final BasketDAO basketDAO;

    public BasketServiceImpl(BasketDAO basketDAO){
        this.basketDAO = basketDAO;
    }


    @Override
    public void save(BasketProduct basketProduct) {
        basketDAO.save(basketProduct);
    }

    @Override
    public void delete(BasketProduct basketProduct) {
        basketDAO.delete(basketProduct);
    }

    @Override
    public List<BasketProduct> findAll() {
        return basketDAO.findAll();
    }

    @Override
    public void deleteProductByUserIdAndProductId(Long userId, Long productId) {
        for(BasketProduct product: basketDAO.findAll()){
            if(product.getUserId() == userId && product.getProductId() == productId){
                basketDAO.delete(product);
            }
        }
    }

    @Override
    public BasketProduct getByProductId(Long productId) {
        return basketDAO.findBasketProductByProductId(productId);
    }

    @Override
    public List<BasketProduct> getAllByUserId(Long userId) {
        return basketDAO.findAllByUserId(userId);
    }
}
