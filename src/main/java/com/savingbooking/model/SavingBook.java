package com.savingbooking.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "saving_book")
public class SavingBook implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "date_of_birth", nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dob;

	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "deposit", nullable = false)
	private double deposit;

	@Column(name = "id_card", nullable = false)
	private String idCard;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "createdAt")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date updateAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
	private TypeOfSavingBook typeOfSavingBook;

	public SavingBook() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public TypeOfSavingBook getTypeOfSavingBook() {
		return typeOfSavingBook;
	}

	public void setTypeOfSavingBook(TypeOfSavingBook typeOfSavingBook) {
		this.typeOfSavingBook = typeOfSavingBook;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return "SavingBook [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address
				+ ", deposit=" + deposit + ", idCard=" + idCard + ", createdAt=" + createdAt + ", updateAt=" + updateAt
				+ ", typeOfSavingBook=" + typeOfSavingBook + "]";
	}

}
