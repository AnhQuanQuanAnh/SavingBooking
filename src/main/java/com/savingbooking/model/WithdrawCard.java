package com.savingbooking.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "withdraw_card")
public class WithdrawCard {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name_customer", nullable = false)
	private String nameCustomer;

	@Column(name = "withdraw_amount", nullable = false)
	private Double withdrawAmount;

	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@OneToMany
	@JoinColumn(name = "id")
	private Set<SavingBook> savingBook;

	public WithdrawCard() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(Double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<SavingBook> getSavingBook() {
		return savingBook;
	}

	public void setSavingBook(Set<SavingBook> savingBook) {
		this.savingBook = savingBook;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	@Override
	public String toString() {
		return "WithdrawCard [id=" + id + ", nameCustomer=" + nameCustomer + ", withdrawAmount=" + withdrawAmount
				+ ", createDate=" + createDate + ", savingBook=" + savingBook + "]";
	}
}
