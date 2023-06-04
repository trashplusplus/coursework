package com.coursework.entity;

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

    @Column(nullable = false)
    private Long userId;
    @Column(nullable = true)
    private Long courierId;
    @Column( nullable = true)
    private Long operatorId; //оператор который взял заказ
    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private String cost;
    @Column(nullable = true)
    private String note;

    @Column(nullable = false)
    private String status;


    @Column(nullable = false)
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
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

    @Override
    public String toString(){
        return String.format("[%s], %s, %s, %s, %s, %s", orderDate,
                destination, cost, note, userId, phoneNumber);
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
