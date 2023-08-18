package com.kmr.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    String userName;
    String password;
	String name;
	String email;
	int age;
	long phoneNumber;
	String country;
}
