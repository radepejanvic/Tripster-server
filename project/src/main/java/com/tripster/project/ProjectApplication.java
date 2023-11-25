package com.tripster.project;

import com.tripster.project.model.Address;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
