package com.example.sprinter.user_story;


import com.example.sprinter.form.UserStoryForm;
import com.example.sprinter.project.Project;
import com.example.sprinter.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("projects/{project_id}/user_story")
public class UserStoryController {

    private final ProjectService projectService;

    private final UserStoryService userStoryService;

    @Autowired
    public UserStoryController(ProjectService projectService, UserStoryService userStoryService) {
        this.projectService = projectService;
        this.userStoryService = userStoryService;
    }

    @GetMapping("/{id}")
    String getOne(@PathVariable Long id, @PathVariable Long project_id, Model model) {
        UserStory userStory = userStoryService.findById(id);
        Project project = projectService.findById(project_id);
        if (userStory == null || project == null) {
            return "404";
        }
        model.addAttribute("userStory", userStory);
        model.addAttribute("tasks", userStory.getTaskSet());
        model.addAttribute("project", project);
        return "single-user-story";
    }

    @PostMapping("/new")
    String add(@Valid @ModelAttribute("form") UserStoryForm userStoryForm, @PathVariable Long project_id) {
        UserStory userStory = userStoryService.createUserStory(userStoryForm, project_id);
        userStoryService.save(userStory);
        return "redirect:/projects/" + project_id;
    }
}
