package com.example.sprinter.form;


import com.example.sprinter.user_story.PriorityEnum;

import javax.validation.constraints.NotNull;

public class UserStoryForm {
    @NotNull
    private String userStoryName;
    private String description;
    private PriorityEnum priority;
    private String projectId;

    public String getUserStoryName() {
        return userStoryName;
    }

    public void setUserStoryName(String userStoryName) {
        this.userStoryName = userStoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
