package com.coursework;

import com.coursework.DAO.MyUserDAO;
import com.coursework.services.MyUserServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MyUserDAO myUserDAO;

    public UserDetailsServiceImpl(MyUserDAO myUserDAO){
        this.myUserDAO = myUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = myUserDAO.getMyUserByUsername(username);

        HashSet<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getRole()));

        return new User(user.getUsername(), user.getPassword(), roles);
    }


}
