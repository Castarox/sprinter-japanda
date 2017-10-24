package com.example.sprinter.configuration;

import com.example.sprinter.user.User;
import com.example.sprinter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@ControllerAdvice
public class ControllerAdviceImpl {

    private final UserService userService;

    @Autowired
    public ControllerAdviceImpl(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User getUser(Principal principal){
        if (principal != null) {
            return userService.getByLogin(principal.getName());
        }
        return new User();
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    protected String handle(final HttpServletResponse response){
        response.setStatus(404);
        return "forward:404";
    }
}
