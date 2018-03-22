package com.savingbooking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "saving_book")
public class SavingBook {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "deposit", nullable = false)
	private Double deposit;

	@Column(name = "id_card", nullable = false)
	private String idCard;

	@Column(name = "date_create", nullable = false)
	private Date dateCreate;

	@OneToOne
	@JoinColumn(name = "id")
	private TypeOfSaving typeOfSaving;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public TypeOfSaving getTypeOfSaving() {
		return typeOfSaving;
	}

	public void setTypeOfSaving(TypeOfSaving typeOfSaving) {
		this.typeOfSaving = typeOfSaving;
	}

	@Override
	public String toString() {
		return "SavingBook [id=" + id + ", name=" + name + ", address=" + address + ", deposit=" + deposit + ", idCard="
				+ idCard + ", dateCreate=" + dateCreate + ", typeOfSaving=" + typeOfSaving + "]";
	}

}
