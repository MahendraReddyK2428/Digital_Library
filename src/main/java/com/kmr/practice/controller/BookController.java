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

import com.kmr.practice.dto.BookRequest;
import com.kmr.practice.entity.Book;
import com.kmr.practice.service.BookService;

@RestController
@RequestMapping("/digitalLibrary/book")
public class BookController {
	
	private final BookService bookService;
	
	

	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	@GetMapping("/test")
	public String test() {
		return "Book controller started...";
	}
	
	@PostMapping("/createBook")
	public Book createStudent(@RequestBody BookRequest bookReq) {
		return bookService.publish(bookReq);
	}
	
	@GetMapping("/getBook")
	public ResponseEntity<Book> getStudent(@RequestParam("title") String title) {
		return bookService.getBook(title);		
	}
	
	@PutMapping("/updateBook")
	public ResponseEntity<Book> updateStudent(@RequestParam("title") String title, @RequestBody BookRequest bookReq){
		return bookService.updateBook(title,bookReq);
	}
	
	@DeleteMapping("/deleteBook")
	public boolean deleteStudent(@RequestParam("title") String title) {
		return bookService.deleteBook(title);
	}
}
