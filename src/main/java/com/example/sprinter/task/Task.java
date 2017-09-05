package com.example.sprinter.task;


import com.example.sprinter.user.User;
//import com.example.sprinter.user_story.UserStory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    private String description;
    private User user;
//    private UserStory userStory;
    private String status;
    private Integer storyPoint;

    public Task() {
    }

    public Task(Long id, String name, String description, User user, String status, Integer storyPoint) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
//        this.userStory = userStory;
        this.status = status;
        this.storyPoint = storyPoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public UserStory getUserStory() {
//        return userStory;
//    }
//
//    public void setUserStory(UserStory userStory) {
//        this.userStory = userStory;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStoryPoint() {
        return storyPoint;
    }

    public void setStoryPoint(Integer storyPoint) {
        this.storyPoint = storyPoint;
    }
}