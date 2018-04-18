package com.savingbooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savingbooking.model.SavingBook;
import com.savingbooking.repository.SavingBookRepository;
import com.savingbooking.service.SavingBookService;

@Service
public class SavingBookServiceImpl implements SavingBookService {

	@Autowired
	private SavingBookRepository savingBookRepository;

	@Override
	public SavingBook save(SavingBook entity) {
		return savingBookRepository.save(entity);
	}

	@Override
	public SavingBook update(SavingBook entity) {
		return savingBookRepository.save(entity);
	}

	@Override
	public void delete(SavingBook entity) {
		savingBookRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		savingBookRepository.delete(id);
	}

	@Override
	public void deleteInBatch(List<SavingBook> entities) {
		savingBookRepository.deleteInBatch(entities);
	}

	@Override
	public SavingBook find(Long id) {
		return savingBookRepository.findOne(id);
	}

	@Override
	public List<SavingBook> findAll() {
		return savingBookRepository.findAll();
	}

	@Override
	public SavingBook findByIdCard(String idCard) {
		return savingBookRepository.findByIdCard(idCard);
	}

}
