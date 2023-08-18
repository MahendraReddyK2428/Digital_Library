package com.kmr.practice.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kmr.practice.dto.CardRequest;
import com.kmr.practice.dto.StudentRequest;
import com.kmr.practice.entity.Card;
import com.kmr.practice.entity.Student;
import com.kmr.practice.repository.CardRepository;
import com.kmr.practice.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository studentRepo;
	private final CardRepository cardRepo;
	
	private CardService cardService;
	
		
	

	public StudentService(StudentRepository studentRepo, CardRepository cardRepo, CardService cardService) {
		super();
		this.studentRepo = studentRepo;
		this.cardRepo = cardRepo;
		this.cardService = cardService;
	}

	public Student createStudent(StudentRequest studentReq) {
		Student std = new Student(studentReq.getUserName(),studentReq.getPassword(), studentReq.getName(), studentReq.getEmail(), studentReq.getAge(), studentReq.getPhoneNumber(), studentReq.getCountry());
		std.setCreatedOn(LocalDate.now());
		std.setUpdatedOn(LocalDate.now());
		
		CardRequest cardReq = new CardRequest();
		cardReq.setEmail(std.getEmail());		
		long cardId = cardService.createCard(cardReq);
		
		Optional<Card> cardOptional = cardRepo.findById((int)cardId);
		Card card = cardOptional.get();
		std.setCard(card);
		
		return studentRepo.save(std);
	}
	
	public ResponseEntity<Student> getStudent(String userName) {	
		Student std = studentRepo.findByUserName(userName);
		if(std != null) {
			return ResponseEntity.ok(std);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Student> updateStudent(String  userName, StudentRequest studentReq) {
		
		Student student = studentRepo.findByUserName(userName);
		
		if(student != null) {
			if(student.getUserName().equals(studentReq.getUserName())) {
				//student = new Student(studentReq.getUserName(),studentReq.getPassword(), studentReq.getName(), studentReq.getEmail(), studentReq.getAge(), studentReq.getPhoneNumber(), studentReq.getCountry());
				student.setUserName(userName);
				student.setPassword(studentReq.getPassword());
				student.setName(studentReq.getName());
				student.setEmail(studentReq.getEmail());
				student.setAge(studentReq.getAge());
				student.setPhoneNumber(studentReq.getPhoneNumber());
				student.setCountry(studentReq.getCountry());
				student.setUpdatedOn(LocalDate.now());
				return ResponseEntity.ok(studentRepo.save(student));
			}else {
				return ResponseEntity.notFound().build();
			}			
		}else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	public boolean deleteStudent(String userName) {
		Student std = studentRepo.findByUserName(userName);
		if(std != null) {
			cardService.deleteCard(std.getCard().getId());
			studentRepo.delete(std);
			return true;
		}else {
			// user not found so not deleted
			return false;
		}
	}
}
