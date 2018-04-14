package com.savingbooking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "deposit_card")
public class DepositCard {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name_customer", nullable = false)
	private String nameCustomer;

	@Column(name = "deposit_amount", nullable = false)
	private Double depositAmount;

	@Column(name = "deposit_date", nullable = false)
	private Date depositDate;

	@ManyToOne
	@JoinColumn(name = "savingbook_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private SavingBook savingBook;

	public DepositCard() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public SavingBook getSavingBook() {
		return savingBook;
	}

	public void setSavingBook(SavingBook savingBook) {
		this.savingBook = savingBook;
	}

	@Override
	public String toString() {
		return "DepositCard [id=" + id + ", nameCustomer=" + nameCustomer + ", depositAmount=" + depositAmount
				+ ", depositDate=" + depositDate + ", savingBook=" + savingBook + "]";
	}

}
