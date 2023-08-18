package com.kmr.practice.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//@Column(nullable = false)
	private String name;
	
	//@Column(nullable = false)
	private String email;
	private int age;
	private String country;
	
	@ManyToMany
	@JoinTable(name = "book_author",
	           joinColumns = @JoinColumn(name = "author_id"),
	           inverseJoinColumns = @JoinColumn(name = "book_id"))
	private Set<Book> books = new HashSet<>();
	
	public Author(String name, String email, int age, String country) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
		this.country = country;
	}
	
	
}
