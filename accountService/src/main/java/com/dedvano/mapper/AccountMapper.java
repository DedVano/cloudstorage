package com.dedvano.mapper;

import com.dedvano.domain.Account;
import com.dedvano.dto.AccountDto;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper
public interface AccountMapper {

    AccountDto toDto(Account account);

    Account toEntity(AccountDto accountDto);

    default Optional<AccountDto> toOptionalDto(Optional<Account> entity) {
        return entity.map(this::toDto);
    }
}
