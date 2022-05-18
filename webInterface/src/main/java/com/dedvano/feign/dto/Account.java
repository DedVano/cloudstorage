package com.dedvano.feign.dto;

import com.dedvano.feign.dto.type.Status;
import lombok.Data;

@Data
public class Account {

    private Long id;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Status status;

    private Role role;
}
