package com.savingbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savingbooking.model.TypeOfSavingBook;

@Repository
public interface TypeOfSavingBookRepository extends JpaRepository<TypeOfSavingBook, Long> {

}
