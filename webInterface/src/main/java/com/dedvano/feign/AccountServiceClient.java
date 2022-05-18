package com.dedvano.feign;

import com.dedvano.feign.dto.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "accountService")
public interface AccountServiceClient {

    @GetMapping("/api/accounts/{login}")
    Optional<Account> getAccount(@PathVariable(name = "login") String login);

    @PostMapping("/api/accounts")
    Account createAccount(@RequestBody Account newAccount);
}
