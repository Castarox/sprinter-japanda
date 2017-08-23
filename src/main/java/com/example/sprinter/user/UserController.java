package com.example.sprinter.user;

import com.example.sprinter.form.EditPasswordForm;
import com.example.sprinter.project.Project;
import com.example.sprinter.project.ProjectService;
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
import java.util.List;
import java.util.Objects;

@Controller
@SessionAttributes("user")
public class UserController {

    private final UserService userService;
    private final ProjectService projectService;

    @Autowired
    public UserController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/index")
    String getAll(Model model, ModelMap modelMap) {
        User user = (User)modelMap.get("user");
        List<Project> projectList = projectService.findAll(user.getId());
        model.addAttribute("projects", projectList);
        return "index";
    }

    @GetMapping("/login")
    String logInToTheSiteViewPage(ModelMap model){
        if (model.get("user") == null) {
            return "login";
        }
        return "redirect:/user";
    }

    @PostMapping("/login")
    String setUserSessionAndMoveToIndex(ModelMap model, @RequestParam String login, @RequestParam String password){
        User user = userService.getByLogin(login, password);
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
        return "redirect:/index";
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
        userService.saveUser(user);
        redirectAttributes.addAttribute("success", "success");
        return "redirect:/user";
    }
}
