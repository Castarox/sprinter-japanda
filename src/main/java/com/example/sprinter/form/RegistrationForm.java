package com.example.sprinter.form;

import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;

public class RegistrationForm {

    @NotNull
    @Size(min=3, message="Name is too short")
    private String name;

    @NotNull
    @Size(min=3, message="Surname is too short")
    private String surname;

    @NotNull
    @Email(message = "Email is not valid")
    private String email;

    @NotNull
    @Size(min=6, message="Password is too short")
    private String password;

    @NotNull
    private String confirm;


    public RegistrationForm(){}

    public RegistrationForm(String name, String email , String password, String surname){
        this.name = name;
        this.email = email;
        this.password = password;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}