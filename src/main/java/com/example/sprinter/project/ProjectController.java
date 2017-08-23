package com.example.sprinter.project;

import com.example.sprinter.form.ProjectForm;
import com.example.sprinter.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@SessionAttributes("user")
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    String getOne(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);
        return "project";
    }

    @PostMapping("/new")
    String add(@Valid @ModelAttribute("form") ProjectForm projectForm, ModelMap model,
               RedirectAttributes redirectAttributes) {
        User user = (User)model.get("user");
        Date startDate = projectService.parseDate(projectForm.getStartDate());
        Date endDate = projectService.parseDate(projectForm.getEndDate());
        String projectName = projectForm.getProjectName();
        if (startDate != null && endDate != null && projectName != null) {
            Project project = new Project(projectName, user.getId(), startDate, endDate, false);
            projectService.add(project);
            return "redirect:/projects/" + project.getId();
        }
        redirectAttributes.addFlashAttribute("message", "Fill out all fields");
        return "redirect:/index";
    }
}
