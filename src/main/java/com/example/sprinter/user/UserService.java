package com.example.sprinter.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public void add(User user) {
        users.add(user);
    }

    public List<User> getAll() {
        return users;
    }

    public User getByLogin(String login) {
        for (User user : users) {
            if (Objects.equals(user.getEmail(), login)) {
                return user;
            }
        }
        return null;
    }
}