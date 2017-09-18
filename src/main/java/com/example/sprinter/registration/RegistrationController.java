package com.example.sprinter.registration;

import com.example.sprinter.form.*;
import com.example.sprinter.user.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    String showRegistartionForm(RegistrationForm registrationForm) {
        return "registration";
    }

    @PostMapping
    String checkForm(
            @Valid RegistrationForm registrationForm,
            BindingResult bindingResult,
            Model model) {
        if (!Objects.equals(registrationForm.getConfirm(), registrationForm.getPassword())) {
            String errMsg = "Both passwords must be the same";
            model.addAttribute("correct", errMsg);
        }
        if (bindingResult.hasErrors() ||
                !Objects.equals(registrationForm.getConfirm(), registrationForm.getPassword())
                ) {
            return "registration";
        }
        System.out.println("MAM TO");
        User newUser = new User();
        newUser.setId(0L);
        newUser.setName(registrationForm.getName());
        newUser.setEmail(registrationForm.getEmail());
        newUser.setPassword(registrationForm.getPassword());
        newUser.setSurname(registrationForm.getSurname());
        userService.saveUser(newUser);
        return "registration";
    }
}
