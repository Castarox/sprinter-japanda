package com.example.sprinter.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/404")
    String notFound() {
        return "404";
    }

    @GetMapping("/500")
    String internalServerError() {
        return "500";
    }

    @GetMapping("/400")
    String badRequest() {
        return "400";
    }
}
