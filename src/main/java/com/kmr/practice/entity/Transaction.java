package com.kmr.practice.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	//private String bookTitle;
	private LocalDate transactionDate = LocalDate.now();
	private LocalDate bookDueDate = transactionDate.plusDays(10);
	private boolean Issued;
	private boolean Returned;
	private double fineAmount;
	private boolean status;
	private LocalDate updatedOn;
	
	@OneToMany(mappedBy =  "transaction")
	private Set<Book> books;
	
	@ManyToOne()
	@JoinColumn(name = "card_id")
	private Card card;
}
