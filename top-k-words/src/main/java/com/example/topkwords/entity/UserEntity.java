package com.example.topkwords.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
    private Long id;

    private String email;

    private String password;

    private String role;

    private String extraInfo;
}