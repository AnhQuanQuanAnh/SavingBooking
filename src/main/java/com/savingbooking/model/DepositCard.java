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
@Table(name = "deposit_card")
public class DepositCard implements Serializable{

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

	@Column(name = "deposit_amount", nullable = false)
	private double depositAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date updateAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "savingbook_id", referencedColumnName = "id", nullable = false)
	private SavingBook savingBook;

	public DepositCard() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
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

	public SavingBook getSavingBook() {
		return savingBook;
	}

	public void setSavingBook(SavingBook savingBook) {
		this.savingBook = savingBook;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Override
	public String toString() {
		return "DepositCard [id=" + id + ", customerName=" + customerName + ", idCard=" + idCard + ", depositAmount="
				+ depositAmount + ", createAt=" + createAt + ", updateAt=" + updateAt + ", savingBook=" + savingBook
				+ "]";
	}

}
