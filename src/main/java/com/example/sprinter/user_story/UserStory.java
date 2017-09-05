package com.example.sprinter.user_story;

import com.example.sprinter.project.Project;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

public class UserStory {

    private Long Id;
    private String name;
    private String description;
    @ManyToMany
    @JoinColumn(name = "project_id")
    private Project project;
}
