package com.example.sprinter.user;


import com.example.sprinter.project.Project;
import com.example.sprinter.task.Task;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app_user_project", joinColumns = {
            @JoinColumn(name="user_id") },
            inverseJoinColumns = { @JoinColumn(name="project_id")})
    private Set<Project> projects;

    @NotNull
    @Size(min=3, message="Name is too short")
    private String name;

    @NotNull
    @Size(min=3, message="Surname is too short")
    private String surname;

    @NotNull
    @Size(min=3, message="Email is too short")
    private String email;

    @NotNull
    @Size(min=6, message="Password is too short")
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Task> tasks;


    public User(){}

    public User(Long id, String name, String email , String password, String surname, Set<Project> projects, Set<Task> tasks){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.surname = surname;
        this.projects = projects;
        this.tasks = tasks;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public long getId() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (projects != null ? !projects.equals(user.projects) : user.projects != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projects != null ? projects.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", projects=" + projects +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}