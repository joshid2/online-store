package com.java.onlinestore.services;

import com.java.onlinestore.model.User;
import com.java.onlinestore.model.UserRoles;
import com.java.onlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User addCustomer(User user){
        user.setUserRoles(UserRoles.CUSTOMER);
        return userRepository.save(user);
    }

    public User addSeller(User user){
        user.setUserRoles(UserRoles.SELLER);
        return userRepository.save(user);
    }

    public User addOwner(User user){
        user.setUserRoles(UserRoles.OWNER);
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
