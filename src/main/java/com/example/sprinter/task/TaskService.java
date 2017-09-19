package com.example.sprinter.task;

import com.example.sprinter.form.TaskForm;
import com.example.sprinter.user_story.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findById(Long id) {
        return taskRepository.findOne(id);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task edit(Long id, TaskForm taskForm) {
       Task task = taskRepository.findOne(id);
       System.out.println(taskForm.getTaskName());
       System.out.println(taskForm.getTaskDescription());
       task.setName(taskForm.getTaskName());
       task.setDescription(taskForm.getTaskDescription());
       return save(task);
    }

    public void remove(Long id) {
        taskRepository.delete(id);
    }
}
