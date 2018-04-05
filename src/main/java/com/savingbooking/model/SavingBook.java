package com.savingbooking.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "saving_book")
public class SavingBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "date_of_birth")
	private LocalDate dob;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;

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

	public Double getDeposit() {
		return deposit;
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
		return "SavingBook [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address
				+ ", deposit=" + deposit + ", idCard=" + idCard + ", dateCreate=" + dateCreate + ", typeOfSaving="
				+ typeOfSaving + "]";
	}

}
