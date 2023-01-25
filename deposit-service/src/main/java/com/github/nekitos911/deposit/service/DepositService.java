package com.github.nekitos911.deposit.service;

import com.github.nekitos911.deposit.dto.DepositResponseDto;

import java.math.BigDecimal;

public interface DepositService {
    DepositResponseDto deposit(Long accountId, Long billId, BigDecimal amount);
}
