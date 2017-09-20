package com.example.sprinter.mail;

import com.example.sprinter.new_user.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.*;
import org.thymeleaf.context.*;

import javax.servlet.http.*;
import java.util.*;

@Controller
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;
    private final NewUserService newUserService;

    @Autowired
    public EmailController(EmailSender emailSender, TemplateEngine templateEngine, NewUserService newUserService) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.newUserService = newUserService;
    }

    @PostMapping("/mailing")
    @ResponseBody
    public Map sendActivateEmail(@RequestBody Map newMap, HttpServletRequest request) {
        Context context = new Context();
        context.setVariable("header", "Registration to Sprinter");
        context.setVariable("title", "Activation link for your account");

        String email = (String) newMap.get("email");
        NewUser newUser = newUserService.findByEmail(email);
        String link = newUserService.generateLink(request);

        context.setVariable("link", link);
        context.setVariable("description", "Activation link");
        String body = templateEngine.process("activate_email", context);

        emailSender.sendEmail(email, "Sprinter - registration", body);
        if (newUser == null) {
            newUser = new NewUser(email,link);
        } else {
            newUser.setLink(link);
        }
        newUserService.save(newUser);
        Map<String, String> map = new HashMap<>();
        map.put("success", "Activation link sent");
        return map;
    }
}