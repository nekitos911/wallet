package com.github.nekitos911.deposit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class DepositResponseDto {
    private BigDecimal amount;
    private String email;
}
