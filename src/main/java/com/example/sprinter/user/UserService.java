package com.example.sprinter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserDetails saveUser(UserDetails userDetails) {

        return userRepository.save(userDetails);
    }

    public List<UserDetails> getAll() {
        List<UserDetails> userDetails = new ArrayList<>();
        userRepository.findAll().forEach(userDetails::add);
        return userDetails;
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails getByLogin(String name) {
        try {
            return userRepository.findByName(name);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}