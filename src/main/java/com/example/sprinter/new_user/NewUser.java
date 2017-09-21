package com.example.sprinter.new_user;

import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class NewUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="new_user_id")
    private Long id;

    @NotNull
    @Email(message = "Email is not valid")
    private String email;

    public NewUser() {}

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @NotNull
    private String link;

    public NewUser(String email, String link) {
        this.email = email;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewUser newUser = (NewUser) o;

        if (id != null ? !id.equals(newUser.id) : newUser.id != null) return false;
        if (email != null ? !email.equals(newUser.email) : newUser.email != null) return false;
        return link != null ? link.equals(newUser.link) : newUser.link == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }
}
