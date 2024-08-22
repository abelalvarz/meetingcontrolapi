package com.taskeasy.meetingcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
public class MeetingControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingControlApplication.class, args);
		System.out.println("Running...");
	}

}
