package com.example.springtest.Form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FindPasswordForm {

    @NotEmpty
    private String findpassword_Id;

    @NotEmpty
    private String findpassword_email;
}
