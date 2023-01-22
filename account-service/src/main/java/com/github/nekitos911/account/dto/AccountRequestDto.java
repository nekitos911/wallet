package com.github.nekitos911.account.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class AccountRequestDto {
    private Long accountId;
    private String name;
    private String phone;
    private String email;
    private List<Long> bills;
    private OffsetDateTime creationDate;
    private LocalDateTime modifiedDate;
}
