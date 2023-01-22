package com.github.nekitos911.account.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String name;
    private String phone;
    private String email;

    @CreationTimestamp
    private OffsetDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @ElementCollection
    private List<Long> bills;
}
