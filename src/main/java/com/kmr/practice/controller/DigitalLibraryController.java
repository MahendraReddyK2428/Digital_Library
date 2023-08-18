package com.kmr.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/digitalLibrary")
public class DigitalLibraryController {

	@GetMapping("/test")
	public String test() {
		return "Digital Library started...";
	}
}
