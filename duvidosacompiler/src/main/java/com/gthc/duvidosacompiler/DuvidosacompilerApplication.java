package com.gthc.duvidosacompiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gthc.duvidosacompiler.application.App;

@SpringBootApplication
public class DuvidosacompilerApplication {

	public static void main(String[] args) {
		App.compile();
		SpringApplication.run(DuvidosacompilerApplication.class, args);
	}

}
