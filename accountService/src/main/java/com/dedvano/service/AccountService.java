package com.dedvano.service;

import com.dedvano.dto.AccountDto;

import java.util.Optional;

public interface AccountService {

    Optional<AccountDto> getByLogin(String login);

    AccountDto saveNew(AccountDto newAccount);

    AccountDto edit(AccountDto account);

    void deleteById(Long id);
}
