package com.example.sprinter.project;

import com.example.sprinter.user.User;
import com.example.sprinter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@SessionAttributes("user")
@RequestMapping("projects")
public class ProjectRestController {

    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    ProjectRestController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @PostMapping("/remove/{id}")
    Boolean removeProject(@PathVariable Long id, ModelMap model) {
        projectService.remove(id);
        User user = (User) model.get("user");
        model.replace("user", userService.getUpdatedUser(user));
        return true;
    }

    @PostMapping("/edit/{id}")
    boolean editProject(@PathVariable Long id) {
        return true;
    }
}
