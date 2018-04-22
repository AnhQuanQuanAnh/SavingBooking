package com.savingbooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savingbooking.model.WithdrawCard;
import com.savingbooking.repository.WithdrawCardRepository;
import com.savingbooking.service.WithdrawCardService;

@Service
public class WithdrawCardServiceImpl implements WithdrawCardService {

	@Autowired
	private WithdrawCardRepository withdrawCardRepository;

	@Override
	public WithdrawCard save(WithdrawCard entity) {
		return withdrawCardRepository.save(entity);
	}

	@Override
	public WithdrawCard update(WithdrawCard entity) {
		return withdrawCardRepository.save(entity);
	}

	@Override
	public void delete(WithdrawCard entity) {
		withdrawCardRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		withdrawCardRepository.delete(id);
	}

	@Override
	public void deleteInBatch(List<WithdrawCard> entities) {
		withdrawCardRepository.deleteAllInBatch();
	}

	@Override
	public WithdrawCard find(Long id) {
		return withdrawCardRepository.findOne(id);
	}

	@Override
	public List<WithdrawCard> findAll() {
		return withdrawCardRepository.findAll();
	}

}
