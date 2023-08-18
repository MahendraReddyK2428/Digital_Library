package com.kmr.practice.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	//@Column(nullable = false)
	private String title;
	
	private int noOfPages;
	
	//@Column(nullable = false)
	private String language;
	
	//@Column(nullable = false)
	private boolean avaliable;
	
	//@Column(nullable = false)
	private String genre;
	
	//@Column(nullable = false)
	private String isbnNumber;
	
	private LocalDate publishedDate;
	private LocalDate updatedDate;

	@ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Author> authors = new HashSet<>();
	
	@ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
	private Set<Card> cards;
	
	@ManyToOne
	@JoinColumn(name = "transation_id")
	private Transaction transaction;
	
	
	public Book(String title, int noOfPages, String language, boolean avaliable, String genre,
			String isbnNumber) {
		super();
		this.title = title;
		this.noOfPages = noOfPages;
		this.language = language;
		this.avaliable = avaliable;
		this.genre = genre;
		this.isbnNumber = isbnNumber;
	}
	
	
	
}
