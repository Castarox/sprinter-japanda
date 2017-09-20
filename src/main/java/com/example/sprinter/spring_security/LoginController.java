package com.example.sprinter.spring_security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;


@Controller
public class LoginController {

    @GetMapping("/login")
    String logInToTheSiteViewPage(Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (principal == null) {
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
}
