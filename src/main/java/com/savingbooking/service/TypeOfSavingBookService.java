package com.savingbooking.service;

import java.util.List;

import com.savingbooking.generic.GenericService;
import com.savingbooking.model.TypeOfSavingBook;

public interface TypeOfSavingBookService extends GenericService<TypeOfSavingBook> {

	List<String> findAllName();
}
