package com.github.nekitos911.account.service.impl;

import com.github.nekitos911.account.entity.Account;
import com.github.nekitos911.account.exception.AccountNotFoundException;
import com.github.nekitos911.account.repository.AccountRepository;
import com.github.nekitos911.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Unable to find account with id: " + accountId));
    }

    public Long createAccount(String name, String email, String phone, List<Long> bills) {
        var account = Account.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .bills(bills)
                .build();

        return accountRepository.save(account).getAccountId();
    }

    public Account updateAccount(Long accountId, String name, String email, String phone, List<Long> bills) {
        var account = Account.builder()
                .accountId(accountId)
                .name(name)
                .email(email)
                .phone(phone)
                .bills(bills)
                .build();

        return accountRepository.save(account);
    }

    @Override
    public Account deleteAccount(Long accountId) {
        var account = getAccountById(accountId);
        accountRepository.deleteById(accountId);
        return account;
    }
}
