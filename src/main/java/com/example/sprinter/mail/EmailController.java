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
        context.setVariable("title", "Activation link for your account");
        context.setVariable("description", "Tutaj jakis opis...");
        String body = templateEngine.process("template", context);
        email = "sigowww@gmail.com";
        emailSender.sendEmail(email, "Sprinter - registration", body);
        return "redirect:/login";
    }
}