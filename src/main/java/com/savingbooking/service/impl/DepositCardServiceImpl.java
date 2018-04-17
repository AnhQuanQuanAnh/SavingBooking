package com.savingbooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savingbooking.model.DepositCard;
import com.savingbooking.repository.DepositCardRepository;
import com.savingbooking.service.DepositCardService;

@Service
public class DepositCardServiceImpl implements DepositCardService {

	@Autowired
	private DepositCardRepository depositCardRepository;
	@Override
	public DepositCard save(DepositCard entity) {
		return depositCardRepository.save(entity);
	}

	@Override
	public DepositCard update(DepositCard entity) {
		return depositCardRepository.save(entity);
	}

	@Override
	public void delete(DepositCard entity) {
		depositCardRepository.delete(entity);		
	}

	@Override
	public void delete(Long id) {
		depositCardRepository.delete(id);		
	}

	@Override
	public void deleteInBatch(List<DepositCard> entities) {
		depositCardRepository.deleteInBatch(entities);		
	}

	@Override
	public DepositCard find(Long id) {
		return depositCardRepository.findOne(id);
	}

	@Override
	public List<DepositCard> findAll() {
		return depositCardRepository.findAll();
	}

}
