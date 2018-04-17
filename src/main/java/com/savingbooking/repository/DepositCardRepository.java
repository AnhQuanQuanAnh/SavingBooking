package com.savingbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savingbooking.model.DepositCard;

@Repository
public interface DepositCardRepository extends JpaRepository<DepositCard, Long> {

}
