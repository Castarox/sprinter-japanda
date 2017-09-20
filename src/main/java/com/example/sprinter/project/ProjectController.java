package com.example.sprinter.project;

import com.example.sprinter.form.ProjectForm;
import com.example.sprinter.user.*;
import com.example.sprinter.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    String getOne(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id);
        if (project == null) {
            return "404";
        }
        model.addAttribute("project", project);
        model.addAttribute("userStories", project.getUserStories());
        return "project";
    }

    @PostMapping("/new")
    String add(@Valid @ModelAttribute("form") ProjectForm projectForm,
               BindingResult binder, ModelMap model,
               RedirectAttributes redirectAttributes) {
        User user = (User) model.get("user");
        if (!binder.hasErrors()) {
            Project project = projectService.createProject(projectForm, user);
            user = userService.updateUserProjects((User) model.get("user"), project);
            model.replace("user", user);
            redirectAttributes.addFlashAttribute("message", "Project created!");
            return "redirect:/projects/" + project.getId();
        } else {
            redirectAttributes.addFlashAttribute("message", "Fill out all fields");
            return "redirect:/";
        }
    }
}
