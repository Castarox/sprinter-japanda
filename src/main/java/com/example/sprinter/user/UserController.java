package com.example.sprinter.user;

import com.example.sprinter.form.EditPasswordForm;
import com.example.sprinter.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    String getAll(Model model, ModelMap modelMap, Principal principal) {
        User user = (User)modelMap.get("user");
        model.addAttribute("projects", user.getProjects());
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

    @PostMapping("/user/password")
    String editPassword(ModelMap model,
                        @ModelAttribute("form") @Valid EditPasswordForm form,
                        BindingResult result,
                        RedirectAttributes redirectAttributes){
        if (!form.getConfirm().equals(form.getPassword()) || result.hasErrors()){
            model.put("error", "Wrong password");
            return "user-edit-profile";
        }
        User user = userService.changePassword((User) model.get("user"), form.getPassword());
        model.replace("user", user);
        redirectAttributes.addAttribute("success", "success");
        return "redirect:/user";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        String errMsg = "Wrong login or password";
        model.addAttribute("error", errMsg);
        return "login";
    }
}
