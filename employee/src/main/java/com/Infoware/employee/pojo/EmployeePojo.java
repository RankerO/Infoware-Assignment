package com.Infoware.employee.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeePojo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String job_title;
	private String phone_number;
	private String email;
	private String address;
	private String city;
	private String state;
	private String primary_emergency_contact;
	private String primary_phone_number;
	private String primary_relationship;
	private String secondary_emergency_contact;
	private String secondary_phone_number;
	private String secondary_relationship;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPrimary_emergency_contact() {
		return primary_emergency_contact;
	}

	public void setPrimary_emergency_contact(String primary_emergency_contact) {
		this.primary_emergency_contact = primary_emergency_contact;
	}

	public String getPrimary_phone_number() {
		return primary_phone_number;
	}

	public void setPrimary_phone_number(String primary_phone_number) {
		this.primary_phone_number = primary_phone_number;
	}

	public String getPrimary_relationship() {
		return primary_relationship;
	}

	public void setPrimary_relationship(String primary_relationship) {
		this.primary_relationship = primary_relationship;
	}

	public String getSecondary_emergency_contact() {
		return secondary_emergency_contact;
	}

	public void setSecondary_emergency_contact(String secondary_emergency_contact) {
		this.secondary_emergency_contact = secondary_emergency_contact;
	}

	public String getSecondary_phone_number() {
		return secondary_phone_number;
	}

	public void setSecondary_phone_number(String secondary_phone_number) {
		this.secondary_phone_number = secondary_phone_number;
	}

	public String getSecondary_relationship() {
		return secondary_relationship;
	}

	public void setSecondary_relationship(String secondary_relationship) {
		this.secondary_relationship = secondary_relationship;
	}


}
