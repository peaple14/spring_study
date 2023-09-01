package com.example.springtest.Form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty
    private String login_Id;

    @NotEmpty
    private String login_password;

    @NotEmpty
    private String login_email;
}