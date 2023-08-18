package com.kmr.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kmr.practice.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	Book findByTitle(String title);
}
