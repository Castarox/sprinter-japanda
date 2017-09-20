package com.example.sprinter.task;

import com.example.sprinter.form.TaskForm;
import com.example.sprinter.user_story.UserStory;
import com.example.sprinter.user_story.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/projects/{project_id}/user_story/{user_story_id}/tasks")
public class TaskController {
    private final TaskService taskService;

    private final UserStoryService userStoryService;

    @Autowired
    TaskController(TaskService taskService, UserStoryService userStoryService) {
        this.taskService = taskService;
        this.userStoryService = userStoryService;
    }

    @PostMapping("/new")
    String add(@Valid @ModelAttribute("form") TaskForm taskForm, @PathVariable Long user_story_id, @PathVariable Long project_id) {
        UserStory userStory = userStoryService.findById(user_story_id);
        Task task = taskService.createTask(taskForm, userStory);
        userStory.getTaskSet().add(task);
        return "redirect:/projects/" + project_id + "/user_story/" + user_story_id;
    }
}
