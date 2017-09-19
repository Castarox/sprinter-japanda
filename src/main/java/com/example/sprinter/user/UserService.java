package com.example.sprinter.user;

import com.example.sprinter.UserDetail.UserDetail;
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

    public User getByLogin(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public User changePassword(User user, String newPassword){
        user.setPassword(newPassword);
        UserDetail userDetail = user.getUserDetail();
        userDetail.setPassword(newPassword);
        user.setUserDetail(userDetail);
        return saveUser(user);
    }

    public User getUpdatedUser(User user) {
        return this.getByLogin(user.getEmail());
    }


}