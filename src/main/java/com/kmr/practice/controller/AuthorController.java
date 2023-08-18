package com.kmr.practice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kmr.practice.dto.AuthorRequest;
import com.kmr.practice.dto.StudentRequest;
import com.kmr.practice.entity.Author;
import com.kmr.practice.entity.Student;
import com.kmr.practice.service.AuthorService;

@RestController
@RequestMapping("/digitalLibrary/author")
public class AuthorController {

	private final AuthorService authorService;	
	
	public AuthorController(AuthorService authorService) {
		super();
		this.authorService = authorService;
	}



	@GetMapping("/test")
	public String test() {
		return "Author Controller started...";
	}
	
	@PostMapping("/createAuthor")
	public Author createAuthor(@RequestBody Author authorReq) {
		return authorService.createAuthor(authorReq);
	}
	
	@GetMapping("/getAuthor")
	public ResponseEntity<Author> getAuthor(@RequestParam("name") String name) {
		return authorService.getAuthor(name);		
	}
	
	@PutMapping("/updateAuthor")
	public ResponseEntity<Author> updateAuthor(@RequestParam("name") String name){
		return authorService.updateAuthor(name);
	}
	
	@DeleteMapping("/deleteAuthor")
	public boolean deleteAuthor(@RequestParam("name") String name) {
		return authorService.deleteAuthor(name);
	}
	
	
}
