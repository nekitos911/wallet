package com.github.nekitos911.bill.repository;

import com.github.nekitos911.bill.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
