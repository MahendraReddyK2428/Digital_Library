package com.kmr.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kmr.practice.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	Student findByUserName(String UserName);
}
