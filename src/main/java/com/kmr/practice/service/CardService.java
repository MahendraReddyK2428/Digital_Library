package com.kmr.practice.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kmr.practice.dto.CardRequest;
import com.kmr.practice.entity.Card;
import com.kmr.practice.repository.CardRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CardService {

	private final CardRepository cardRepo;
	
	
	public CardService(CardRepository cardRepo) {
		super();
		this.cardRepo = cardRepo;
	}



	public long createCard(CardRequest cardReq) {
		Card cardObj = new Card();
		cardObj.setEmail(cardReq.getEmail());
		var card = cardRepo.save(cardObj);
		return card.getId();
		
	}
	
	public void renewCard(long card_id) {
		Card card = new Card();
		Optional<Card> cardOptional = cardRepo.findById((int)card_id);
		
		if(cardOptional.isPresent()) {
			Card cardObj = cardOptional.get();
			card.setEmail(cardObj.getEmail());
		}else {
			throw new EntityNotFoundException("card with "+card_id+" not found");
		}
		
	}
	
	
	public ResponseEntity<Optional<Card>> getCard(long card_id){
		Optional<Card> card = cardRepo.findById((int)card_id);
		
		if(card != null) {
			return ResponseEntity.ok(card);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public boolean deleteCard(long card_id) {
		cardRepo.deleteById((int)card_id);
		return true;
	}
}
