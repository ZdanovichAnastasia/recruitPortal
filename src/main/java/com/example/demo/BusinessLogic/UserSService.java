package com.example.demo.BusinessLogic;

import com.example.demo.DataAccess.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDTO = userService.getUserByUserName(username);

        if (userDTO == null) {
            throw new UsernameNotFoundException("Unknown user: "+username);
        }
        String role = "admin";
        if(userDTO.getUserType().equals("рекрутер"))
            role = "recruiter";
        UserDetails user = org.springframework.security.core.userdetails.User.builder()
                .username(userDTO.getLogin())
                .password(userDTO.getPassword())
                .roles(role)
                .build();
        return user;
    }
}

