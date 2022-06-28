package com.krest.shiro.service;

import com.krest.shiro.entity.Account;

public interface AccountService {
    Account findByUsername(String username);

    void createAccount(Account account);
}
