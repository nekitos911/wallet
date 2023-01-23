package com.github.nekitos911.account.controller;

import com.github.nekitos911.account.dto.AccountRequestDto;
import com.github.nekitos911.account.dto.AccountResponseDto;
import com.github.nekitos911.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDto> getAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(new AccountResponseDto(accountService.getAccountById(accountId)));
    }

    @PostMapping
    public ResponseEntity<Long> createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok(accountService.createAccount(
                accountRequestDto.getName(),
                accountRequestDto.getEmail(),
                accountRequestDto.getPhone(),
                accountRequestDto.getBills()
        ));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountResponseDto> updateAccount(@PathVariable Long accountId, @RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok(new AccountResponseDto(accountService.updateAccount(
                accountId,
                accountRequestDto.getName(),
                accountRequestDto.getEmail(),
                accountRequestDto.getPhone(),
                accountRequestDto.getBills()
        )));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<AccountResponseDto> deleteAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(new AccountResponseDto(accountService.deleteAccount(accountId)));
    }
}
