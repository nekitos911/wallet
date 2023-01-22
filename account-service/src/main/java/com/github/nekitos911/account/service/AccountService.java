package com.github.nekitos911.account.service;

import com.github.nekitos911.account.entity.Account;

import java.util.List;

public interface AccountService {
    Account getAccountById(Long accountId);
    Long createAccount(String name, String email, String phone, List<Long> bills);
    Account updateAccount(Long accountId, String name, String email, String phone, List<Long> bills);
    Account deleteAccount(Long accountId);

}
