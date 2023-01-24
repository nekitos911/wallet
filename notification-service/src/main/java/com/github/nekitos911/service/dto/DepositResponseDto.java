package com.github.nekitos911.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositResponseDto {
    private BigDecimal amount;
    private String email;
}
