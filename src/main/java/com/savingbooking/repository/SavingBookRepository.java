package com.savingbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savingbooking.model.SavingBook;

@Repository
public interface SavingBookRepository extends JpaRepository<SavingBook, Long>{

}
