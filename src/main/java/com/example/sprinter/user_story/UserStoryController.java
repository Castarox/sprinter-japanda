package com.example.sprinter.user_story;


import com.example.sprinter.form.UserStoryForm;
import com.example.sprinter.project.Project;
import com.example.sprinter.project.ProjectService;
import com.example.sprinter.task.Task;
import com.example.sprinter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@SessionAttributes("user")
@RequestMapping("projects/{project_id}/user_story")
public class UserStoryController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserStoryService userStoryService;

    @GetMapping("/{id}")
    String getOne(@PathVariable Long id, @PathVariable Long project_id, Model model) {
        UserStory userStory = userStoryService.findById(id);
        Project project = projectService.findById(project_id);
        model.addAttribute("userStory", userStory);
        model.addAttribute("tasks", userStory.getTaskSet());
        model.addAttribute("project", project);
        return "user-story";
    }

    @PostMapping("/new")
    String add(@Valid @ModelAttribute ("form") UserStoryForm userStoryForm, @PathVariable Long project_id) {
        UserStory userStory = userStoryService.createUserStory(userStoryForm, project_id);
        userStoryService.add(userStory);
        return "redirect:/projects/" + project_id;
    }


}
