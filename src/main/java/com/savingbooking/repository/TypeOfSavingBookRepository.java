package com.savingbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.savingbooking.model.TypeOfSavingBook;
import java.lang.String;
import java.util.List;

@Repository
public interface TypeOfSavingBookRepository extends JpaRepository<TypeOfSavingBook, Long> {

	@Query(value="Select name from type_of_saving_book")
	List<String> findAllName();
}
