package com.example.sprinter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SprinterApplication {

	public static void main(String[] args) {
		com.synerise.SyneriseTracker.init("799F14DD-AE63-1248-EBDA-A043095671CC", "2.0.1", false);
		SpringApplication.run(SprinterApplication.class, args);
	}
}
