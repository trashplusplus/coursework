package com.coursework.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ordersProducts")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "order_id")
    private Long orderId;
}
