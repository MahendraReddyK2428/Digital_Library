package com.kmr.practice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kmr.practice.dto.AuthorRequest;
import com.kmr.practice.entity.Author;
import com.kmr.practice.repository.AuthorRepository;

@Service
public class AuthorService {
	
	private final AuthorRepository authorRepo;

	public AuthorService(AuthorRepository authorRepo) {
		super();
		this.authorRepo = authorRepo;
	}
	
	public Author createAuthor(Author author) {
		Author authorObj = new Author(author.getName(), author.getEmail(), author.getAge(), author.getCountry());
		//authorObj.setBooks(author.getBooks());
		return authorRepo.save(authorObj);
	}
	
	public ResponseEntity<Author> updateAuthor(String email){
		Author author = authorRepo.findByEmail(email);
		
		if(author != null) {
			//Author authorObj = new Author(author.getName(), author.getEmail(), author.getAge(), author.getCountry());
			author.setName(author.getName());
			author.setAge(author.getAge());
			author.setCountry(author.getCountry());
			
			return ResponseEntity.ok(authorRepo.save(author));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public boolean deleteAuthor(String email) {
		Author author = authorRepo.findByEmail(email);
		if(author != null) {
			authorRepo.delete(author);
			return true;
		}else {
			return false;
		}
	}
	
	public ResponseEntity<Author> getAuthor(String email){
		Author author = authorRepo.findByEmail(email);
		if(author != null) {
			return ResponseEntity.ok(author);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
