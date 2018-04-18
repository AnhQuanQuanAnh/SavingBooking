package com.savingbooking.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "type_of_saving_book")
public class TypeOfSavingBook implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date updateAt;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<SavingBook> savingBook = new HashSet<>();

	public TypeOfSavingBook() {

	}

	public TypeOfSavingBook(String name) {
		this.name = name;
	}

	public TypeOfSavingBook(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
