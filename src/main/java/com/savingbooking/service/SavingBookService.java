package com.savingbooking.service;

import com.savingbooking.generic.GenericService;
import com.savingbooking.model.SavingBook;

public interface SavingBookService extends GenericService<SavingBook> {

	SavingBook findByIdCard(String idCard);
}
