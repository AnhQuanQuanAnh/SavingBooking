package com.savingbooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savingbooking.model.TypeOfSavingBook;
import com.savingbooking.repository.TypeOfSavingBookRepository;
import com.savingbooking.service.TypeOfSavingBookService;

@Service
public class TypeOfSavingBookServiceImpl implements TypeOfSavingBookService {

	@Autowired
	private TypeOfSavingBookRepository typeOfSavingBookRepository;
	@Override
	public TypeOfSavingBook save(TypeOfSavingBook entity) {
		return typeOfSavingBookRepository.save(entity);
	}

	@Override
	public TypeOfSavingBook update(TypeOfSavingBook entity) {
		return typeOfSavingBookRepository.save(entity);
	}

	@Override
	public void delete(TypeOfSavingBook entity) {
		typeOfSavingBookRepository.delete(entity);	
	}

	@Override
	public void delete(Long id) {
		typeOfSavingBookRepository.delete(id);		
	}

	@Override
	public void deleteInBatch(List<TypeOfSavingBook> entities) {
		typeOfSavingBookRepository.deleteInBatch(entities);		
	}

	@Override
	public TypeOfSavingBook find(Long id) {
		return typeOfSavingBookRepository.findOne(id);
	}

	@Override
	public List<TypeOfSavingBook> findAll() {
		return typeOfSavingBookRepository.findAll();
	}
}
