package com.github.nekitos911.bill.service;

import com.github.nekitos911.bill.entity.Bill;

import java.math.BigDecimal;

public interface BillService {
    Bill getBillById(Long billId);
    Long createBill(Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnabled);
    Bill updateBill(Long billId, Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnabled);
    Bill deleteBill(Long billId);
}
