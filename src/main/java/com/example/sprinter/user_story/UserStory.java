package com.example.sprinter.user_story;

import com.example.sprinter.project.Project;
import com.example.sprinter.task.Task;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_story_id")
    private Long Id;
    private String name;
    private String description;
    private String priority;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @OneToMany(mappedBy = "userStory", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("name ASC")
    private Set<Task> taskSet;

    UserStory() {
    }

    UserStory(String name, String description, String priority, Project project) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.project = project;
        this.taskSet = new HashSet<>();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<Task> getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(Set<Task> taskSet) {
        this.taskSet = taskSet;
    }
}
