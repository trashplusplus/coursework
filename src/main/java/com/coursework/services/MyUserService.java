package com.coursework.services;

import com.coursework.entity.MyUser;

import java.util.List;


public interface MyUserService{
    void save(MyUser myUser);
    void delete(MyUser myUser);

    MyUser getById(long id);
    MyUser findByUsername(String username);
    MyUser findByEmail(String email);

    List<MyUser> findAll();
    List<MyUser> findAllCouriers();
}
