package com.savingbooking.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "withdraw_card")
public class WithdrawCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "customer_name", nullable = false)
	private String customerName;

	@Column(name = "id_card", nullable = false)
	private String idCard;

	@Column(name = "withdraw_amount", nullable = false)
	private double withdrawAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date updateAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "savingbook_id", referencedColumnName = "id")
	private SavingBook savingBook;

	public WithdrawCard() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public SavingBook getSavingBook() {
		return savingBook;
	}

	public void setSavingBook(SavingBook savingBook) {
		this.savingBook = savingBook;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
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
		return "WithdrawCard [id=" + id + ", customerName=" + customerName + ", idCard=" + idCard + ", withdrawAmount="
				+ withdrawAmount + ", createAt=" + createAt + ", updateAt=" + updateAt + ", savingBook=" + savingBook
				+ "]";
	}

}
