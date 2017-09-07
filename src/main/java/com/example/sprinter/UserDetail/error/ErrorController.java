package com.example.sprinter.UserDetail.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class ErrorController {

    @GetMapping("/404")
    String notFound(){
        return "404";
    }

    @GetMapping("/500")
    String internalServerError(){
        return "500";
    }

    @GetMapping("/400")
    String badRequest(){
        return "400";
    }
}
