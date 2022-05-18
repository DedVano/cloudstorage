package com.dedvano.controller;

import com.dedvano.feign.AccountServiceClient;
import com.dedvano.feign.dto.Account;
import com.dedvano.feign.dto.Role;
import com.dedvano.feign.dto.type.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceClient accountServiceClient;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public String saveNewAccount(Account newAccount) {
        Role role = new Role();
        role.setId(4L);
        role.setName("USER");
        newAccount.setStatus(Status.OK);
        newAccount.setRole(role);
        newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
        accountServiceClient.createAccount(newAccount);
        return "redirect:/";
    }
}
