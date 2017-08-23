package com.example.sprinter.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Project {
    private Long id;
    private Long ownerId;
    @NotNull
    private String name;
    private Date startDate;
    private Date endDate;
    private Boolean copy;

    public Project() {}

    public Project(String name, Long ownerId, Date startDate, Date endDate, Boolean copy) {
        this.name = name;
        this.ownerId = ownerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.copy = copy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean isCopy() {
        return copy;
    }

    public void setCopy(Boolean copy) {
        this.copy = copy;
    }
}
