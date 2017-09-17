package com.example.sprinter.project;

import com.example.sprinter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@RestController
@SessionAttributes("user")
@RequestMapping("projects/remove")
public class ProjectRestController {

    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    ProjectRestController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @PostMapping("/{id}")
    Boolean removeProject(@PathVariable Long id, ModelMap model){
        projectService.remove(id);
        return true;
    }
}
