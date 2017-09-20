package com.example.sprinter.new_user;

import com.example.sprinter.user.*;
import org.h2.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.servlet.http.*;
import java.util.*;

@Service
public class NewUserService {
    private final NewUserRepository newUserRepository;

    @Autowired
    public NewUserService(NewUserRepository newUserRepository) {
        this.newUserRepository = newUserRepository;
    }

    public String generateLink(HttpServletRequest request) {
        String userLink = makeRegistrationPage(request);
        Random random = new Random();
        Integer randomNumber;
        NewUser checkingUser;
        do {
            randomNumber = random.nextInt(999999999);
            checkingUser = newUserRepository.findByLink(userLink + randomNumber.toString());
        } while (checkingUser != null);
        userLink += randomNumber.toString();
        return userLink;
    }

    public NewUser findByEmail(String email) {
        return newUserRepository.findByEmail(email);
    }

    public NewUser save(NewUser newUser) {
//        NewUser newUser = new NewUser(email, link);
        return newUserRepository.save(newUser);
    }

    private String makeUrl(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    private String makeRegistrationPage(HttpServletRequest request) {
        String actualUrl = makeUrl(request);
        String toBeReplaced = "mailing";
        String pageUrl = actualUrl.replace(toBeReplaced, "");
        return pageUrl + "registration/";
    }
}
