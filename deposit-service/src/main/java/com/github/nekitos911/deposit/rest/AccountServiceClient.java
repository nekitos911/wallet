package com.github.nekitos911.deposit.rest;

import com.github.nekitos911.deposit.dto.AccountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", path = "/api/v1/accounts")
public interface AccountServiceClient {
    @GetMapping("/{accountId}")
    ResponseEntity<AccountResponseDto> getAccountById(@PathVariable Long accountId);
}
