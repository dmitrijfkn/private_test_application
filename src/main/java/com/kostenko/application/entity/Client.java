package com.kostenko.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Client{
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    private String password;
}
