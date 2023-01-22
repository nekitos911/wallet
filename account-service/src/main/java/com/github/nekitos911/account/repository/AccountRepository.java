package com.github.nekitos911.account.repository;

import com.github.nekitos911.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
