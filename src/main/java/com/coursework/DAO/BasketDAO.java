package com.coursework.DAO;

import com.coursework.entity.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketDAO extends JpaRepository<BasketProduct, Long> {

    //List<BasketProduct> findAllByUserId();
    BasketProduct findBasketProductByProductId(Long id);
    List<BasketProduct> findAllByUserId(Long id);


}
