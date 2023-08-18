package com.kmr.practice.dto;

import java.util.List;

import com.kmr.practice.entity.Author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

	
	private String title;
	private int noOfPages;
	private String language;
	private boolean avaliable;
	private String genre;
	private String isbnNumber;
	private List<AuthorRequest> authors; 
}
