package com.tripster.project;

import com.tripster.project.service.AccommodationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDate;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) { SpringApplication.run(ProjectApplication.class, args);}

}
