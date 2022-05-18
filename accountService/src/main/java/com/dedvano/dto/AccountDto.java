package com.dedvano.dto;

import com.dedvano.domain.Role;
import com.dedvano.domain.type.Status;
import lombok.Data;

@Data
public class AccountDto {

    private Long id;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Status status;

    private Role role;
}
