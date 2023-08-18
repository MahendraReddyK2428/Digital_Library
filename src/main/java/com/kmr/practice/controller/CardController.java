package com.kmr.practice.controller;

import java.util.Optional;

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
import com.kmr.practice.dto.CardRequest;
import com.kmr.practice.entity.Book;
import com.kmr.practice.entity.Card;
import com.kmr.practice.repository.CardRepository;
import com.kmr.practice.service.CardService;

@RestController
@RequestMapping("/digitalLibrary/card")
public class CardController {

	private final CardService cardService;
	
	
	public CardController(CardService cardService) {
		super();
		this.cardService = cardService;
	}

	@GetMapping("/test")
	public String test() {
		return "Book controller started...";
	}
	
	@PostMapping("/createCard")
	public void createCard(@RequestBody CardRequest cardReq) {
		cardService.createCard(cardReq);
	}
	
	@GetMapping("/getCard")
	public ResponseEntity<Optional<Card>> getCard(@RequestParam("card_id") long card_id) {
		return cardService.getCard(card_id);		
	}
	
	@PutMapping("/renewCard")
	public void renewCard(@RequestParam("card_id") long card_id){
		cardService.renewCard(card_id);
	}
	
	@DeleteMapping("/deleteCard")
	public boolean deleteCard(@RequestParam("card_id") long card_id) {
		return cardService.deleteCard(card_id);
	}
}
