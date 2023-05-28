package com.coursework;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Order> allOrders;

    public Basket(){
        allOrders = new ArrayList<>();
    }

    public void addOrder(Order order){
        allOrders.add(order);
    }



}
