package com.dedvano.security.service.impl;

import com.dedvano.feign.AccountServiceClient;
import com.dedvano.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountServiceClient accountServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountServiceClient.getAccount(username).map(UserPrincipal::new)
                .orElseThrow(() -> new IllegalArgumentException("Has no user with login " + username));
    }
}
