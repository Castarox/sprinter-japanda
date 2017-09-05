package com.example.sprinter.user_story;

import com.example.sprinter.project.Project;
import com.example.sprinter.task.Task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_story_id")
    private Long Id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @OneToMany(mappedBy = "userStory")
    private Set<Task> taskSet;

    UserStory(){}

    UserStory(String name, String description, Project project){
        this.name = name;
        this.description = description;
        this.project = project;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
