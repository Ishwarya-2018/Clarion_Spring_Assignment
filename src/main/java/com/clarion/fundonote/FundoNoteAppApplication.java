package com.clarion.fundonote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FundoNoteAppApplication {
	public static void main(String[] args) {	
		SpringApplication.run(FundoNoteAppApplication.class, args);
	}
}
