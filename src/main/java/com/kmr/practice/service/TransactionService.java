package com.kmr.practice.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.kmr.practice.customExceptions.BookNotFoundException;
import com.kmr.practice.customExceptions.CardNotFoundException;
import com.kmr.practice.customExceptions.TransactionFailedException;
import com.kmr.practice.customExceptions.TransactionNotFoundException;
import com.kmr.practice.entity.Book;
import com.kmr.practice.entity.Card;
import com.kmr.practice.entity.Transaction;
import com.kmr.practice.repository.BookRepository;
import com.kmr.practice.repository.CardRepository;
import com.kmr.practice.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	private final CardRepository cardRepo;
	private final BookRepository bookRepo;
	private final TransactionRepository transactionRepo;
	



	public TransactionService(CardRepository cardRepo, BookRepository bookRepo, TransactionRepository transactionRepo) {
		super();
		this.cardRepo = cardRepo;
		this.bookRepo = bookRepo;
		this.transactionRepo = transactionRepo;
	}




	@Transactional
	public void issueBook(long cardId, List<String> bookTitles) {
		Optional<Card> cardOptional = cardRepo.findById((int)cardId);
		
		if(cardOptional.isPresent()) {
			Card card = cardOptional.get();
			HashSet<Book> books = new HashSet<>();
			for(String bookTitle : bookTitles) {
				Book book = bookRepo.findByTitle(bookTitle);
				if(book != null) {
					card.getBooks().add(book);
					books.add(book);
				}
			}			
			
			if(books.size() > 0) {
				//Card card = cardOptional.get();
				if(card.getPower() > 0) {
					
					if(card.getFineamount() == 0) {
						Transaction transaction = new Transaction();
						transaction.setCard(card);
						transaction.setBooks(books);
						transaction.setIssued(true);
						transaction.setReturned(false);
						transaction.setStatus(false);
						
						transactionRepo.save(transaction);
						
						card.setPower(card.getPower()-1);
						
						cardRepo.save(card);
						
						for(Book book: books) {
							book.setAvaliable(false);
							bookRepo.save(book);
						}
					}else {
						throw new TransactionFailedException("Transaction failed: FineAmount need to be paid");
					}
				}else {
					throw new TransactionFailedException("Transaction failed: Insufficient card power");
				}
			}else {
				throw new TransactionFailedException("Transaction failed: Book not found");
			}
		}else {
			throw new TransactionFailedException("Transaction failed: Card not found");
		}		
	}
	
	@Transactional
	public void returnBook(long transaction_id) {
		Optional<Transaction> transactionOptional = transactionRepo.findById((int)transaction_id);
		
		if(transactionOptional.isPresent()) {
			Transaction transaction = transactionOptional.get();
			
			Optional<Card> cardOptional = cardRepo.findById((int)transaction.getCard().getId());
			
			if(cardOptional.isPresent()) {
				Card card = cardOptional.get();
				
				Set<Book> books = transaction.getBooks();
				
								
				if(books.size() > 0) {
					
					long extraDays = ChronoUnit.DAYS.between(transaction.getBookDueDate(), LocalDate.now());
					double finePerDayPerBook = 10;						
					double fineAmount = extraDays*finePerDayPerBook;
					
					
					for(Book book : books) {
						
						Set<Book> booksInCard = new HashSet<>();
						booksInCard = card.getBooks();
						booksInCard.remove(book);
						
						card.setBooks(booksInCard);
						card.setFineamount(card.getFineamount()+fineAmount);
						card.setPower(card.getPower()-1);
						
						cardRepo.save(card);
						
						book.setCards(new HashSet<>());
						book.setAvaliable(true);
						book.setTransaction(null);
						bookRepo.save(book);	
					}
					transaction.setFineAmount(fineAmount*books.size());
					transaction.setReturned(true);
					transaction.setStatus(true);
					transaction.setUpdatedOn(LocalDate.now());
					transactionRepo.save(transaction);
				}else {
					throw new BookNotFoundException("Transaction Failed: No Book are issued to the cardId: "+card.getId());
				}
			}else {
				throw new CardNotFoundException("Transaction Failed: Card not found");
			}
		}else {
			throw new TransactionNotFoundException("Transaction Not Found");
		}
		
	}
}
