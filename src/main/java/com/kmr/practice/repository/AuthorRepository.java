package com.kmr.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kmr.practice.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findByEmail(String email);
}
