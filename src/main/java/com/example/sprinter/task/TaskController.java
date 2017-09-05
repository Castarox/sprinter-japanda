package com.example.sprinter.task;

import com.example.sprinter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")

public class TaskController {

    @Autowired
    UserService userService;

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
}
