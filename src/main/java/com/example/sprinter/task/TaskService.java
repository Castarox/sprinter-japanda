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

    public Task add(Task task) {
        return taskRepository.save(task);
    }

    public void update(Task task) {
        taskRepository.save(task);
    }

    public void remove(Long id) {
        taskRepository.delete(id);
    }

    public Task createTask(TaskForm taskForm, UserStory userStory) {
        String name = taskForm.getTaskName();
        String description = taskForm.getTaskDescription();

        return add(new Task(name, description, userStory, State.NEW, 0));
    }
}
