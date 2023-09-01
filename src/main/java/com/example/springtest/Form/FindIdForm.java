package com.example.springtest.Form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FindIdForm {

    @NotEmpty
    private String findid_email;
}
