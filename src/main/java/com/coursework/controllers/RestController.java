package com.coursework.controllers;


import com.coursework.entity.MyUser;
import com.coursework.services.MyUserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/couriers")
public class RestController {

    private final MyUserServiceImpl userService;

    public RestController(MyUserServiceImpl userService){
        this.userService = userService;
    }
    //возвращает всех курьеров
    @GetMapping
    public List<MyUser> getAllCouriers(){
       return userService.findAllCouriers();
    }
}
