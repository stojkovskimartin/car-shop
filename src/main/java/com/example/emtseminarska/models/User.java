package com.example.emtseminarska.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty(message = "Please provide an e-mail")
    private String email;

    @NotNull
    private String password;

    @NotEmpty(message = "Please provide your first name")
    @NotNull
    private String firstName;

    @NotEmpty(message = "Please provide your last name")
    @NotNull
    private String lastName;

    @NotNull
    private String confirmPassword;

    private boolean enabled;

    private String confirmationToken;

    private String role;
}