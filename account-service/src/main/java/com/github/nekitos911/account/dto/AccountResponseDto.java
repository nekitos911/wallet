package com.github.nekitos911.account.dto;

import com.github.nekitos911.account.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class AccountResponseDto {
    private Long accountId;
    private String name;
    private String phone;
    private String email;
    private List<Long> bills;
    private OffsetDateTime creationDate;
    private LocalDateTime modifiedDate;

    public AccountResponseDto(Account account) {
        this.accountId = account.getAccountId();
        this.creationDate = account.getCreationDate();
        this.modifiedDate = account.getModifiedDate();
        this.name = account.getName();
        this.phone = account.getPhone();
        this.email = account.getEmail();
        this.bills = account.getBills();
    }
}
