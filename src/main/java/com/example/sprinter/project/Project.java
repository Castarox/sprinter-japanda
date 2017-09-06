package com.example.sprinter.project;

import com.example.sprinter.user.UserDetails;
import com.example.sprinter.user_story.UserStory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="project_id")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "projects")
    private Set<UserDetails> owners;

    @OneToMany(mappedBy = "project")
    private Set<UserStory> userStories;

    @NotNull
    private String name;
    private String startDate;
    private String endDate;
    private Boolean copy;

    public Project() {}

    public Project(String name, Set<UserDetails> owners, String startDate, String endDate, Boolean copy) {
        this.name = name;
        this.owners = owners;
        this.startDate = startDate;
        this.endDate = endDate;
        this.copy = copy;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<UserDetails> getOwners() {
        return owners;
    }

    public void setOwners(Set<UserDetails> owners) {
        this.owners = owners;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Boolean isCopy() {
        return copy;
    }

    public void setCopy(Boolean copy) {
        this.copy = copy;
    }

    public Set<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(Set<UserStory> userStories) {
        this.userStories = userStories;
    }
}
