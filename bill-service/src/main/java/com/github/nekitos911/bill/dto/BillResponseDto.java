package com.github.nekitos911.bill.dto;

import com.github.nekitos911.bill.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class BillResponseDto {
    private Long billId;
    private Long accountId;
    private BigDecimal amount;
    private Boolean isDefault;
    private Boolean overdraftEnabled;
    private OffsetDateTime creationDate;
    private OffsetDateTime modifiedDate;
    public BillResponseDto(Bill bill) {
        billId = bill.getBillId();
        accountId = bill.getAccountId();
        amount = bill.getAmount();
        isDefault = bill.getIsDefault();
        overdraftEnabled = bill.getOverdraftEnabled();
        creationDate = bill.getCreationDate();
        modifiedDate = bill.getModifiedDate();
    }
}
