package com.kmr.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kmr.practice.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>{
	
}
