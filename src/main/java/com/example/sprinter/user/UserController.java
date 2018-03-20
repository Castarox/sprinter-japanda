package com.example.sprinter.user;

import com.example.sprinter.form.EditPasswordForm;
import com.synerise.SyneriseTracker;
import com.synerise.producers.Client;
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

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    String getAll(Model model, ModelMap modelMap) {
        User user = (User)modelMap.get("user");
        model.addAttribute("projects", user.getProjects());
        Client client = new Client();
        client.setCustomIdentify(String.valueOf(user.getId()));
        client.setAge(30);
        client.setEmail(user.getEmail());
        client.setFirstname(user.getName());
        client.setSecondname(user.getName());
        SyneriseTracker.getInstance().track(client);
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
        userService.changePassword((User) model.get("user"), form.getPassword());
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
