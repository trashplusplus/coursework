package com.coursework.services;

import com.coursework.DAO.MyUserDAO;
import com.coursework.entity.MyUser;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MyUserServiceImpl implements MyUserService{
    private final MyUserDAO myUserDAO;

    public MyUserServiceImpl(MyUserDAO myUserDAO){
        this.myUserDAO = myUserDAO;
    }

    @Override
    public void save(MyUser myUser) {
        myUserDAO.save(myUser);
    }

    @Override
    public void delete(MyUser myUser) {
        myUserDAO.delete(myUser);
    }


    @Override
    public MyUser getById(long id) {
        return myUserDAO.getById(id);
    }

    @Override
    public MyUser findByUsername(String username) {
        return myUserDAO.getMyUserByUsername(username);
    }

    @Override
    public MyUser findByEmail(String email){
        return myUserDAO.findMyUserByEmail(email);
    }

    @Override
    public List<MyUser> findAll() {
        return myUserDAO.findAll();
    }
}
