package com.kmr.practice.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	private int age;
	
	@Column(nullable = false)
	private long phoneNumber;
	
	private String country;
	
	private LocalDate createdOn;
	
	private LocalDate updatedOn;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "card_id")
	private Card card;
	
	public Student(String userName, String password, String name, String email, int age, long phoneNumber, String country) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.email = email;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.country = country;
	}
	
	
	
	
	
}
