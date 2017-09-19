package com.example.sprinter.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SessionAttributes("user")
@RequestMapping("projects/{id}/user_story/tasks")
public class TaskRestController {

    private final TaskService taskService;

    @Autowired
    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/remove/{id}")
    Boolean removeTask(@PathVariable Long id){
        System.out.println("dupa");
        taskService.remove(id);
        return true;
    }
}
