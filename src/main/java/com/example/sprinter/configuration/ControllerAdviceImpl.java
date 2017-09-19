package com.example.sprinter.configuration;

import com.example.sprinter.user.User;
import com.example.sprinter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@ControllerAdvice
public class ControllerAdviceImpl {

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

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    protected String handle(final HttpServletRequest request, final HttpServletResponse response){
        response.setStatus(404);
        return "forward:404";
    }
}
