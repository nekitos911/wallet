package com.github.nekitos911.deposit.controller;

import com.github.nekitos911.deposit.dto.DepositRequestDto;
import com.github.nekitos911.deposit.dto.DepositResponseDto;
import com.github.nekitos911.deposit.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/deposits")
public class DepositController {
    private final DepositService depositService;

    @PostMapping
    public ResponseEntity<DepositResponseDto> deposit(@RequestBody DepositRequestDto depositRequestDto) {
        return ResponseEntity.ok(depositService.deposit(depositRequestDto.getAccountId(), depositRequestDto.getBillId(), depositRequestDto.getAmount()));
    }
}
