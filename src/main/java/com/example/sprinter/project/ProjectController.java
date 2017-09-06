package com.example.sprinter.project;

import com.example.sprinter.form.ProjectForm;
import com.example.sprinter.user.UserDetails;
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
@SessionAttributes("userDetails")
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping("/{id}")
    String getOne(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);
        model.addAttribute("userStories", project.getUserStories());
        return "project";
    }

    @PostMapping("/new")
    String add(@Valid @ModelAttribute("form") ProjectForm projectForm, ModelMap model,
               RedirectAttributes redirectAttributes) {
        UserDetails userDetails = (UserDetails)model.get("userDetails");
        String startDate = projectForm.getStartDate();
        String endDate = projectForm.getEndDate();
        String projectName = projectForm.getProjectName();
        Set<UserDetails> owners = new HashSet<>();
        owners.add(userDetails);
        if (startDate != null && endDate != null && projectName != null) {
            Project project = new Project(projectName, owners, startDate, endDate, false);
            Set<Project> projects = userDetails.getProjects();
            projectService.add(project);
            projects.add(project);
            userDetails.setProjects(projects);
            model.replace("userDetails", userService.saveUser(userDetails));
            redirectAttributes.addFlashAttribute("message", "Project created!");
            return "redirect:/projects/" + project.getId();
        }
        redirectAttributes.addFlashAttribute("message", "Fill out all fields");
        return "redirect:/";
    }
}
