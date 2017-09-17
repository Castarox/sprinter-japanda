package com.example.sprinter.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SessionAttributes("user")
@RequestMapping("projects")
public class ProjectRestController {

    private final ProjectService projectService;

    @Autowired
    ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/{id}")
    Boolean removeProject(@PathVariable Long id){
        projectService.remove(id);
        return true;
    }
}
