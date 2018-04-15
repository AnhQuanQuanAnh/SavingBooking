package com.savingbooking.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "type_of_saving_book")
public class TypeOfSavingBook implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<SavingBook> savingBook = new HashSet<>();

	public TypeOfSavingBook() {

	}

	public TypeOfSavingBook(String name) {
		this.name = name;
	}

	public TypeOfSavingBook(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SavingBook> getSavingBook() {
		return savingBook;
	}

	public void setSavingBook(Set<SavingBook> savingBook) {
		this.savingBook = savingBook;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
