package com.github.nekitos911.deposit.rest;

import com.github.nekitos911.deposit.dto.BillRequestDto;
import com.github.nekitos911.deposit.dto.BillResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "bill-service", path = "/api/v1/bills")
public interface BillServiceClient {

    @GetMapping("/{billId}")
    ResponseEntity<BillResponseDto> getBillById(@PathVariable Long billId);

    @PutMapping("/{billId}")
    ResponseEntity<BillResponseDto> update(@PathVariable Long billId, BillRequestDto billRequestDto);

    @GetMapping("/{accountId}")
    ResponseEntity<List<BillResponseDto>> getBillByAccountID(@PathVariable Long accountId);
}
