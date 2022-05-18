package com.dedvano.controller;

import com.dedvano.dto.AccountDto;
import com.dedvano.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{login}")
    public Optional<AccountDto> getAccount(@PathVariable(name = "login") String login) {
        return accountService.getByLogin(login);
    }

    @PostMapping
    public AccountDto createAccount(@RequestBody AccountDto newAccount) {
        return accountService.saveNew(newAccount);
    }

    @PutMapping
    public AccountDto editAccount(@RequestBody AccountDto account) {
        return accountService.edit(account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable(name = "id") Long id) {
        accountService.deleteById(id);
    }
}
