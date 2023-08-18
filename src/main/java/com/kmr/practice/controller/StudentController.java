package com.kmr.practice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kmr.practice.dto.StudentRequest;
import com.kmr.practice.entity.Student;
import com.kmr.practice.service.StudentService;

@RestController
@RequestMapping("/digitalLibrary/student")
public class StudentController {
	
	private final StudentService studentService;
	
	
	
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}



	@GetMapping("/test")
	public String test() {
		return "Student controller started...";
	}
	
	@PostMapping("/createStudent")
	public Student createStudent(@RequestBody StudentRequest studentReq) {
		return studentService.createStudent(studentReq);
	}
	
	@GetMapping("/getStudent")
	public ResponseEntity<Student> getStudent(@RequestParam("userName") String UserName) {
		return studentService.getStudent(UserName);		
	}
	
	@PutMapping("/updateStudent")
	public ResponseEntity<Student> updateStudent(@RequestParam("userName") String userName, @RequestBody StudentRequest studentReq){
		return studentService.updateStudent(userName,studentReq);
	}
	
	@DeleteMapping("/deleteStudent")
	public boolean deleteStudent(@RequestParam("userName") String userName) {
		return studentService.deleteStudent(userName);
	}
}
