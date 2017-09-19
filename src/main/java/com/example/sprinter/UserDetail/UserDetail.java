package com.example.sprinter.UserDetail;

import com.example.sprinter.user.User;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "user_details")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 3, message = "Name is too short")
    @Column(name = "email")
    private String name;

    @NotNull
    @Size(min = 6, message = "Password is too short")
    private String password;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userDetail")
    private User user;

    public UserDetail() {
    }

    public UserDetail(User user, String name, String password) {
        this.user = user;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetail that = (UserDetail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}