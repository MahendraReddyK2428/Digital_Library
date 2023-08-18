package com.kmr.practice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kmr.practice.customExceptions.AuthorNotFound;
import com.kmr.practice.dto.AuthorRequest;
import com.kmr.practice.dto.BookRequest;
import com.kmr.practice.entity.Author;
import com.kmr.practice.entity.Book;
import com.kmr.practice.repository.AuthorRepository;
import com.kmr.practice.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepo;
	private final AuthorRepository authorRepo;
	
	private final AuthorService authorService;


	public BookService(BookRepository bookRepo, AuthorRepository authorRepo, AuthorService authorService) {
		super();
		this.bookRepo = bookRepo;
		this.authorRepo = authorRepo;
		this.authorService = authorService;
	}



	public Book publish(BookRequest book) {
		Book bookObj = new Book(book.getTitle(), book.getNoOfPages(), book.getLanguage(), book.isAvaliable(), book.getGenre(), book.getIsbnNumber());
		bookObj.setPublishedDate(LocalDate.now());
		bookObj.setUpdatedDate(LocalDate.now());
		bookRepo.save(bookObj);

		for(AuthorRequest author: book.getAuthors()) {
			Author authorObj = new Author(author.getName(), author.getEmail(), author.getAge(), author.getCountry());
			authorObj = authorService.createAuthor(authorObj);
			
			authorObj.getBooks().add(bookObj);
			bookObj.getAuthors().add(authorObj);
			
			authorRepo.save(authorObj);
			
		}
		
		bookRepo.save(bookObj);
		return bookObj;
	}
	
	public ResponseEntity<Book> getBook(String title){
		Book book = bookRepo.findByTitle(title);
		if(book != null) {
			return ResponseEntity.ok(book);
		}else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	public ResponseEntity<Book> updateBook(String title, BookRequest bookReq){
		Book book = bookRepo.findByTitle(title);
		if(book != null) {
			if(book.getTitle().equals(bookReq.getTitle())) {
				//Book bookObj = new Book(book.getTitle(), book.getNoOfPages(), book.getLanguage(), book.isAvaliable(), book.getGenre(), book.getIsbnNumber());
				book.setTitle(bookReq.getTitle());
				book.setNoOfPages(bookReq.getNoOfPages());
				book.setLanguage(bookReq.getLanguage());
				book.setAvaliable(bookReq.isAvaliable());
				book.setGenre(bookReq.getGenre());
				book.setIsbnNumber(bookReq.getIsbnNumber());
				book.setUpdatedDate(LocalDate.now());
				bookRepo.save(book);

				for(AuthorRequest author: bookReq.getAuthors()) {
					Author authorObj = authorRepo.findByEmail(author.getEmail());
					if(authorObj != null) {
						authorObj.setName(author.getName());
						authorObj.setAge(author.getAge());
						authorObj.setCountry(author.getCountry());
					}else {
						throw new AuthorNotFound("Upadate failed: author not found");
					}
					/*Author authorObj = new Author(author.getName(), author.getEmail(), author.getAge(), author.getCountry());
					authorObj = authorService.createAuthor(authorObj);
					*/
					//authorObj.getBooks().add(book);
					//book.getAuthors().add(authorObj);
					
					authorRepo.save(authorObj);
					
				}			
				
				return ResponseEntity.ok(bookRepo.save(book));
			}else {
				return ResponseEntity.notFound().build();
			}
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 *  when book deleted author is also deleted 
	 *  need to modify the code in a way author don't get deleted when book is deleted
	 *  
	 * @param title
	 * @return
	 */
	
	public boolean deleteBook(String title) {
		Book book = bookRepo.findByTitle(title);
		if(book != null) {
			List<Author> authors = new ArrayList<>();
			book.getAuthors();
			for(Author author : authors) {
				author.getBooks().remove(book);
				//authorRepo.save(author);
			}
			authors.clear();
			bookRepo.delete(book);
			return true;
		}else {
			return false;
		}
	}
}
