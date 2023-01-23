package com.github.nekitos911.bill.dto;

import com.github.nekitos911.bill.entity.Bill;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class BillRequestDto {
    private Long billId;
    private Long accountId;
    private BigDecimal amount;
    private Boolean isDefault;
    private Boolean overdraftEnabled;

    public BillRequestDto(Bill bill) {
        billId = bill.getBillId();
        accountId = bill.getAccountId();
        isDefault = bill.getIsDefault();
        overdraftEnabled = bill.getOverdraftEnabled();
    }
}
