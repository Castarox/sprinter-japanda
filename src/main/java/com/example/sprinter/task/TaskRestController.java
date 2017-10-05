package com.example.sprinter.task;

import com.example.sprinter.form.TaskForm;
import com.example.sprinter.form.TaskStateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("/edit/{id}/state")
    Boolean editState(@PathVariable Long id, @RequestBody TaskStateForm taskStateForm){
        taskService.changeState(id, taskStateForm);
        System.out.println(taskStateForm.getState());
        return true;
    }

    @GetMapping("/edit/{id}/state")
    Boolean haha (){
        return true;
    }
}
