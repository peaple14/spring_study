package com.example.springtest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private String id;

    private String password;

    @Column(unique = true)
    private String email;

}
