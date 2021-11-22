package com.example.emtseminarska.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
//    @NotNull
//    @Size(min = 1,message = "*Engine type is required")
    private String engineName;

    @Column(length = 2000)
    private String engineDescrp;
}
