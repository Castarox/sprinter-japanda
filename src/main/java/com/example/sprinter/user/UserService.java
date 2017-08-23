package com.example.sprinter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private UserRepository userRepository;

    public void add(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByLogin(String login, String password) {
        try {
            User user = userRepository.findByEmailAndPassword(login, password);
            return user;
        } catch (NullPointerException ex) {
            System.out.println("Not found");
        }
        return null;
    }
}