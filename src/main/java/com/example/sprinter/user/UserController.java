package com.example.sprinter.user;

import com.example.sprinter.form.EditPasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    String logInToTheSiteViewPage(ModelMap model){
        if (model.get("user") == null) {
            return "login";
        }
        return "redirect:/user";
    }

    @PostMapping("/login")
    String setUserSessionAndMoveToIndex(ModelMap model, @RequestParam String login, @RequestParam String password){
        User user = userService.getByLogin(login);
        Boolean err = false;
        String errMsg = "Wrong login or password";
        if (user == null) {
            err = true;
        } else if(!Objects.equals(user.getPassword(), password)) {
            err = true;
        }
        if (err) {
            model.put("error", errMsg);
            return "login";
        }
        model.put("user", user);
        return "index";
    }

    @GetMapping("/user")
    String indexUser() {
        return "user-profile";
    }

    @GetMapping("/user/password")
    String indexEdit(){
        return "user-edit-profile";
    }


}
