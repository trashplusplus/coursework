package com.coursework;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "orders")
public class Order {
    public Order(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "courierId", nullable = false)
    private Long courierId;
    @Column(name = "operatorId", nullable = false)
    private Long operatorId; //оператор который взял заказ
    @Column(name = "orderDate", nullable = false)
    private LocalDate orderDate;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private String cost;
    @Column(nullable = false)
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
