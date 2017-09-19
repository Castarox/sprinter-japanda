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

    @PostMapping(value = "/email", produces = "application/json")
    @ResponseBody
    Map checkEmailInDb(@RequestBody Map body) {
        Map map = new HashMap();
        System.out.println((String) body.get("email"));
        User user = userService.getByLogin((String) body.get("email"));
        if (user == null) {
            map.put("isEmail", false);
        } else {
            map.put("isEmail", true);
        }
        return map;
    }

    @GetMapping(value = "/link", produces = "application/json")
    @ResponseBody
    Map generateActivateLinkForEmail() {
        Map map = new HashMap();
        map.put("isEmail", "Such a user already exists");
        return map;
    }

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
