package com.dedvano.service.impl;

import com.dedvano.dto.AccountDto;
import com.dedvano.mapper.AccountMapper;
import com.dedvano.repository.AccountRepository;
import com.dedvano.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Optional<AccountDto> getByLogin(String login) {
        return accountMapper.toOptionalDto(accountRepository.getByLogin(login));
    }

    @Override
    public AccountDto saveNew(AccountDto newAccount) {
        if(this.getByLogin(newAccount.getLogin()).isEmpty()) {
            return accountMapper.toDto(accountRepository.save(accountMapper.toEntity(newAccount)));
        } else {
            throw new IllegalArgumentException("User already exists");
        }
    }

    @Override
    public AccountDto edit(AccountDto account) {
        return accountMapper.toDto(accountRepository.save(accountMapper.toEntity(account)));
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
