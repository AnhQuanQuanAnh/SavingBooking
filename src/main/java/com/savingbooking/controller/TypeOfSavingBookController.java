package com.savingbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.savingbooking.model.TypeOfSavingBook;
import com.savingbooking.service.TypeOfSavingBookService;

@Controller
public class TypeOfSavingBookController {

	@Autowired
	private TypeOfSavingBookService typeOfSavingBookService;
	
	public List<TypeOfSavingBook> getAll(){
		return typeOfSavingBookService.findAll();
	}
	
	public void findAll(){
		for(TypeOfSavingBook item : getAll()){
			System.out.println("aaaaaaaaaaaa"+item.getId());
		}
	}
}
