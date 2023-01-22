package com.github.nekitos911.account.controller;

import com.github.nekitos911.account.dto.AccountRequestDto;
import com.github.nekitos911.account.dto.AccountResponseDto;
import com.github.nekitos911.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public AccountResponseDto getAccount(@PathVariable Long accountId) {
        return new AccountResponseDto(accountService.getAccountById(accountId));
    }

    @PostMapping
    public Long createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return accountService.createAccount(
                accountRequestDto.getName(),
                accountRequestDto.getEmail(),
                accountRequestDto.getPhone(),
                accountRequestDto.getBills()
        );
    }

    @PutMapping("/{accountId}")
    public AccountResponseDto updateAccount(@PathVariable Long accountId, @RequestBody AccountRequestDto accountRequestDto) {
        return new AccountResponseDto(accountService.updateAccount(
                accountId,
                accountRequestDto.getName(),
                accountRequestDto.getEmail(),
                accountRequestDto.getPhone(),
                accountRequestDto.getBills()
        ));
    }

    @DeleteMapping("/{accountId}")
    public AccountResponseDto deleteAccount(@PathVariable Long accountId) {
        return new AccountResponseDto(accountService.deleteAccount(accountId));
    }
}
