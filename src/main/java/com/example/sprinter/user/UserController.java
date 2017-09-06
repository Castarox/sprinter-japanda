package com.example.sprinter.user;

import com.example.sprinter.form.EditPasswordForm;
import com.example.sprinter.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;


    @GetMapping("")
    String getAll(Model model, ModelMap modelMap, Principal principal) {
        User user = (User)modelMap.get("user");
        if (user == null){
            User newUser = userService.getByLogin(principal.getName());
            modelMap.put("user", newUser);
        }
        model.addAttribute("projects", ((User) modelMap.get("user")).getProjects());
        return "index";
    }

    @GetMapping("/login")
    String logInToTheSiteViewPage(ModelMap model){
        if (model.get("user") == null) {
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if( auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
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
        User user = (User) model.get("user");
        user.setPassword(form.getPassword());
        model.replace("user",userService.saveUser(user));
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
