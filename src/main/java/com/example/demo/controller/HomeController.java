package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class HomeController {

	@GetMapping(value = "/")
	public String home() {
		return "hello";
	}

	@GetMapping(value = "/private")
	public String privateArea() {
		return "private";
	}

}
