package com.example.sprinter.task;

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
