package com.github.nekitos911.deposit.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositRequestDto {
    private Long accountId;
    private Long billId;
    private BigDecimal amount;
}
