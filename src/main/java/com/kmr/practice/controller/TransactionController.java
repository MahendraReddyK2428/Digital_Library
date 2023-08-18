package com.kmr.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kmr.practice.dto.BookIssue;
import com.kmr.practice.service.TransactionService;

@RestController
@RequestMapping("/digitalLibrary/transaction")
public class TransactionController {

	private final TransactionService transactionService;
	
	
	
	public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}

	@GetMapping("/test")
	public String test() {
		return "Transaction test good...";
	}
	
	@PostMapping("/issueBook")
	public void issueBook(@RequestBody BookIssue bookIssue) {
		transactionService.issueBook(bookIssue.getCardId(), bookIssue.getBookTitles());
	}
	
	@PutMapping("/returnBook")
	public void returnBook(@RequestParam("transactionId") long transactionId) {
		transactionService.returnBook(transactionId);
	}
}
