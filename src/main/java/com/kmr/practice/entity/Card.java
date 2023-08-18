package com.kmr.practice.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String email;
	private boolean status = true;
	private double fineamount = 0;
	private LocalDate createdOn = LocalDate.now();
	private LocalDate updatedOn = LocalDate.now();
	private LocalDate validUpto = createdOn.plusYears(1);
	private int power = 3;
	
	@OneToOne(mappedBy = "card", cascade = CascadeType.ALL)
	private Student student;
	
	@OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Transaction> transaction;
	
	@ManyToMany
	@JoinTable(
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "card_id"))
	private Set<Book> books;


	
}
