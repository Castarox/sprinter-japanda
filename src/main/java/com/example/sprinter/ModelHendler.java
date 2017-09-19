package com.example.sprinter;

import com.example.sprinter.user.User;
import com.example.sprinter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class ModelHendler {

    private User user;
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User getUser(Principal principal){
        if (principal != null) {
            if (user == null) {
                user = userService.getByLogin(principal.getName());
            }
            return user;
        }
        return new User();
    }
}
