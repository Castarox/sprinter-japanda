package com.example.sprinter.project;

import com.example.sprinter.form.ProjectForm;
import com.example.sprinter.user.User;
import com.example.sprinter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projects")
public class ProjectRestController {

    private final ProjectService projectService;

    @Autowired
    ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/remove/{id}")
    boolean removeProject(@PathVariable Long id) {
        projectService.remove(id);
        return true;
    }

    @PostMapping("/edit/{id}")
    boolean editProject(@PathVariable Long id, @RequestBody ProjectForm projectForm) {
        projectService.edit(id, projectForm);
        return true;
    }
}
