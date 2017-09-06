package com.example.sprinter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public User saveUser(User user) {

        return userRepository.save(user);
    }

    public List<User> getAll() {
        List<User> userDetails = new ArrayList<>();
        userRepository.findAll().forEach(userDetails::add);
        return userDetails;
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByLogin(String name) {
        try {
            return userRepository.findByName(name);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}