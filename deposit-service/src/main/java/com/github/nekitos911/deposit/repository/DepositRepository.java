package com.github.nekitos911.deposit.repository;

import com.github.nekitos911.deposit.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
