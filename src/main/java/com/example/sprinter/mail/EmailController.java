package com.example.sprinter.mail;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.*;
import org.thymeleaf.context.*;

@Controller
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @GetMapping("/mailing/{email}")
    public String sendActivateEmail(@PathVariable String email) {
        Context context = new Context();
        context.setVariable("header", "Registration to Sprinter");
        context.setVariable("title", "#8 Spring Boot – email - szablon i wysyłanie");
        context.setVariable("description", "Tutaj jakis opis...");
        String body = templateEngine.process("template", context);
        emailSender.sendEmail(email, "CodeCouple Newsletter", body);
        return "login";
    }
}