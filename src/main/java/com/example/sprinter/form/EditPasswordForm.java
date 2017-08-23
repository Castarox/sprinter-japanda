package com.example.sprinter.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditPasswordForm {

    @NotNull
    @Size(min = 8, message = "Password to short")
    private String password;

    @NotNull
    @Size(min = 8, message = "Password to short")
    private String confirm;

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
