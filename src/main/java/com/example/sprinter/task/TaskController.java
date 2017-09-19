package com.example.sprinter.task;

import com.example.sprinter.form.TaskForm;
import com.example.sprinter.user.User;
import com.example.sprinter.user_story.UserStory;
import com.example.sprinter.user_story.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
@RequestMapping("/projects/{project_id}/user_story/{user_story_id}/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    private UserStoryService userStoryService;

    @Autowired
    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/new")
    String add(@Valid @ModelAttribute("form") TaskForm taskForm, @PathVariable Long user_story_id, @PathVariable Long project_id, ModelMap model,
               RedirectAttributes redirectAttributes) {
        UserStory userStory = userStoryService.findById(user_story_id);
        Task task = taskService.createTask(taskForm, userStory);
        userStory.getTaskSet().add(task);
        return "redirect:/projects/" + project_id + "/user_story/" + user_story_id;
    }
}
