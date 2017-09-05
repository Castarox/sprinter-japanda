package com.example.sprinter.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void add(Task task) {
        taskRepository.save(task);
    }

    public void update(Task task) {
        taskRepository.save(task);
    }

    public void remove(Long id) {
        taskRepository.delete(id);
    }
}
