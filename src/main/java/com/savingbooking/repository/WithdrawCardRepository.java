package com.savingbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savingbooking.model.WithdrawCard;

@Repository
public interface WithdrawCardRepository extends JpaRepository<WithdrawCard, Long> {

}
