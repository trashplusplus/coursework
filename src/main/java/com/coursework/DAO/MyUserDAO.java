package com.coursework.DAO;

import com.coursework.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyUserDAO extends JpaRepository<MyUser, Long> {
    MyUser getMyUserByUsername(String username);
    MyUser findMyUserByEmail(String email);
    List<MyUser> findAll();
}
