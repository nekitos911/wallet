package com.github.nekitos911.bill.service.impl;

import com.github.nekitos911.bill.entity.Bill;
import com.github.nekitos911.bill.exception.BillNotFoundException;
import com.github.nekitos911.bill.repository.BillRepository;
import com.github.nekitos911.bill.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;

    @Override
    public Bill getBillById(Long billId) {
        return billRepository.findById(billId).orElseThrow(() ->
                new BillNotFoundException("bill not found with id: " + billId));
    }

    @Override
    public Long createBill(Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnabled) {
        var bill = Bill.builder()
                .accountId(accountId)
                .amount(amount)
                .isDefault(isDefault)
                .overdraftEnabled(overdraftEnabled)
                .build();

        return billRepository.save(bill).getBillId();
    }

    @Override
    public Bill updateBill(Long billId, Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnabled) {
        return billRepository.save(Bill.builder()
                .billId(billId)
                .accountId(accountId)
                .amount(amount)
                .isDefault(isDefault)
                .overdraftEnabled(overdraftEnabled)
                .build());
    }

    @Override
    public Bill deleteBill(Long billId) {
        var bill = getBillById(billId);
        billRepository.deleteById(billId);
        return bill;
    }
}
