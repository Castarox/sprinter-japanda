package com.example.sprinter.registration;

import com.example.sprinter.UserDetail.*;
import com.example.sprinter.form.*;
import com.example.sprinter.new_user.*;
import com.example.sprinter.user.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import javax.servlet.http.*;
import javax.validation.*;
import java.util.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;
    private NewUserService newUserService;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public RegistrationController(UserService userService, NewUserService newUserService, UserDetailsServiceImpl userDetailsService) {
        this.userService = userService;
        this.newUserService = newUserService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/email", produces = "application/json")
    @ResponseBody
    Map checkEmailInDb(@RequestBody Map body) {
        Map map = new HashMap();
        User user = userService.getByLogin((String) body.get("email"));
        if (user == null) {
            map.put("isEmail", false);
        } else {
            map.put("isEmail", true);
        }
        return map;
    }

    @GetMapping("/{linkId}")
    String showRegistartionForm(RegistrationForm registrationForm, HttpServletRequest request) {
        NewUser newUser = newUserService.findByLink(request);
        registrationForm.setEmail(newUser.getEmail());
        return "registration";
    }

    @GetMapping("/activated")
    String accountActivated() {
        return "account_activated";
    }

    @PostMapping
    String checkForm(
            @Valid RegistrationForm registrationForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (!Objects.equals(registrationForm.getConfirm(), registrationForm.getPassword())) {
            String errMsg = "Both passwords must be the same";
            model.addAttribute("correct", errMsg);
        }
        if (bindingResult.hasErrors() ||
                !Objects.equals(registrationForm.getConfirm(), registrationForm.getPassword())
                ) {
            return "registration";
        }
        User user = userService.saveNewUser(registrationForm);
        userDetailsService.createAndSaveUserDetailForUser(user);
        NewUser newUser = newUserService.findByEmail(user.getEmail());
        newUserService.delete(newUser);
        redirectAttributes.addAttribute("activate", "true");
        return "redirect:/login";
    }
}
