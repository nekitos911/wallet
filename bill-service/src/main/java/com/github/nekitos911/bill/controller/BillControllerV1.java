package com.github.nekitos911.bill.controller;

import com.github.nekitos911.bill.dto.BillRequestDto;
import com.github.nekitos911.bill.dto.BillResponseDto;
import com.github.nekitos911.bill.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bills")
public class BillControllerV1 {
    private final BillService billService;

    @GetMapping("/{billId}")
    public ResponseEntity<BillResponseDto> getBill(@PathVariable Long billId) {
        return ResponseEntity.ok(new BillResponseDto(billService.getBillById(billId)));
    }

    @PostMapping
    public ResponseEntity<Long> createBill(@RequestBody BillRequestDto billRequestDto) {
        return ResponseEntity.ok(billService.createBill(
                billRequestDto.getAccountId(),
                billRequestDto.getAmount(),
                billRequestDto.getIsDefault(),
                billRequestDto.getOverdraftEnabled()));
    }

    @PutMapping("/{billId}")
    public ResponseEntity<BillResponseDto> updateBill(@PathVariable Long billId, @RequestBody BillRequestDto billRequestDto) {
        return ResponseEntity.ok(new BillResponseDto(billService.updateBill(
                billId,
                billRequestDto.getAccountId(),
                billRequestDto.getAmount(),
                billRequestDto.getIsDefault(),
                billRequestDto.getOverdraftEnabled())));
    }

    @DeleteMapping("/{billId}")
    public ResponseEntity<BillResponseDto> deleteBill(@PathVariable Long billId) {
        return ResponseEntity.ok(new BillResponseDto(billService.deleteBill(billId)));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<BillResponseDto>> getBillsByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(
                billService.getBillsByAccountID(accountId).stream()
                .map(BillResponseDto::new)
                .collect(Collectors.toList())
        );
    }
}
