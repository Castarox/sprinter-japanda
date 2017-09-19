package com.example.sprinter.task;

import com.example.sprinter.form.TaskForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projects/{id}/user_story/tasks")
public class TaskRestController {

    private final TaskService taskService;

    @Autowired
    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/remove/{id}")
    Boolean removeTask(@PathVariable Long id) {
        taskService.remove(id);
        return true;
    }

    @PostMapping("/edit/{id}")
    Boolean editTask(@PathVariable Long id, @RequestBody TaskForm taskForm) {
        taskService.edit(id, taskForm);
        return true;
    }
}
