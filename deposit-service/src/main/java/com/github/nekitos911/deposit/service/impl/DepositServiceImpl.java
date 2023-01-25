package com.github.nekitos911.deposit.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.nekitos911.deposit.dto.AccountResponseDto;
import com.github.nekitos911.deposit.dto.BillResponseDto;
import com.github.nekitos911.deposit.dto.DepositResponseDto;
import com.github.nekitos911.deposit.entity.Deposit;
import com.github.nekitos911.deposit.exception.AccountNotFoundException;
import com.github.nekitos911.deposit.exception.BillNotFoundException;
import com.github.nekitos911.deposit.repository.DepositRepository;
import com.github.nekitos911.deposit.dto.BillRequestDto;
import com.github.nekitos911.deposit.exception.DepositServiceException;
import com.github.nekitos911.deposit.rest.AccountServiceClient;
import com.github.nekitos911.deposit.rest.BillServiceClient;
import com.github.nekitos911.deposit.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DepositServiceImpl implements DepositService {
    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";
    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";
    private final DepositRepository depositRepository;
    private final AccountServiceClient accountServiceClient;
    private final BillServiceClient billServiceClient;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectWriter objectWriter = new ObjectMapper().writerFor(DepositResponseDto.class);


    @Override
    public DepositResponseDto deposit(Long accountId, Long billId, BigDecimal amount) {
        if (accountId == null && billId == null) {
            throw new DepositServiceException("Account or bill is null");
        }

        var billRequest = billId == null
                ? mapFromBillRequestDto(getDefaultBill(accountId), amount)
                : mapFromBillRequestDto(billServiceClient.getBillById(billId).getBody(), amount);

        billServiceClient.update(billRequest.getBillId(), billRequest);
        AccountResponseDto account = accountServiceClient.getAccountById(billRequest.getAccountId()).getBody();

        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        depositRepository.save(Deposit.builder()
                .billId(billRequest.getBillId())
                .email(account.getEmail())
                .amount(amount)
                .billId(billRequest.getBillId())
                .build());

        var deposit = DepositResponseDto.builder()
                .amount(amount)
                .email(account.getEmail())
                .build();

        try {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT, objectWriter.writeValueAsString(deposit));
        } catch (Exception ex) {
            throw new DepositServiceException(ex);
        }
        return deposit;
    }

    private BillRequestDto mapFromBillRequestDto(BillResponseDto billResponseDto, BigDecimal amount) {
        if (billResponseDto == null) {
            throw new BillNotFoundException("Bill is not found");
        }

        return BillRequestDto
                .builder()
                .billId(billResponseDto.getBillId())
                .accountId(billResponseDto.getAccountId())
                .creationDate(billResponseDto.getCreationDate())
                .modifiedDate(billResponseDto.getModifiedDate())
                .isDefault(billResponseDto.getIsDefault())
                .overdraftEnabled(billResponseDto.getOverdraftEnabled())
                .amount(billResponseDto.getAmount().add(amount))
                .build();
    }

    private BillResponseDto getDefaultBill(Long accountId) {
        return Optional
                .ofNullable(billServiceClient.getBillByAccountID(accountId).getBody())
                .orElse(Collections.emptyList())
                .stream()
                .filter(BillResponseDto::getIsDefault)
                .findAny()
                .orElseThrow(() -> new BillNotFoundException("Default bill is not found"));
    }
}
