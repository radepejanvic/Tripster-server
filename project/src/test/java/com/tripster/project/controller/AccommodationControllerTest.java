package com.tripster.project.controller;

import com.tripster.project.dto.AccommodationDTO;
import com.tripster.project.dto.PriceDTO;
import com.tripster.project.dto.TokenDTO;
import com.tripster.project.dto.UserDTO;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Day;
import com.tripster.project.model.User;
import com.tripster.project.model.enums.DayStatus;
import com.tripster.project.service.AccommodationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpMethod.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AccommodationControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;
    private String accessToken;
    @Autowired
    private AccommodationService accommodationService;
    @BeforeEach
    public void login() {
        UserDTO dto = new UserDTO();
        dto.setEmail("host@hotmail.com");
        dto.setPassword("admin");
        ResponseEntity<TokenDTO> responseEntity = restTemplate.exchange("/api/login",
                POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<TokenDTO>() {
                });
        this.accessToken = "Bearer " + responseEntity.getBody().getToken();
    }
    private HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        return headers;
    }

    @Test
    @DisplayName("Add calendar")
    public void test_add_calendar(){
        List<PriceDTO> dtos = new ArrayList<>();
        LocalDate now = LocalDate.now();
        dtos.add(new PriceDTO(now,now.plusDays(4),50));
        ResponseEntity<Integer> responseEntity = restTemplate.exchange("/api/accommodations/price/1",
                POST,
                new HttpEntity<>(dtos,getHttpHeaders()),
                new ParameterizedTypeReference<Integer>() {
                });

        Integer response = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(5, response);

        List<Day> list =  accommodationService.findCalendar(1l);
        assertEquals(5,list.size());
    }

    @Test
    @DisplayName("Add calendar - accommodationId is invalid")
    public void test_add_calendar_accommodation_does_not_exist(){
        List<PriceDTO> dtos = new ArrayList<>();
        LocalDate now = LocalDate.now();
        dtos.add(new PriceDTO(now,now.plusDays(4),50));
        ResponseEntity<Integer> responseEntity = restTemplate.exchange("/api/accommodations/price/15",
                POST,
                new HttpEntity<>(dtos,getHttpHeaders()),
                new ParameterizedTypeReference<Integer>() {
                });

        Integer response = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(0, response);

    }
    @Test
    @DisplayName("Update calendar - accommodationId is invalid")
    public void test_update_calendar_accommodation_does_not_exist(){
        List<PriceDTO> dtos = new ArrayList<>();
        LocalDate now = LocalDate.now();
        dtos.add(new PriceDTO(now,now.plusDays(5),50));
        ResponseEntity<Integer> responseEntity = restTemplate.exchange("/api/accommodations/price/15",
                PUT,
                new HttpEntity<>(dtos,getHttpHeaders()),
                new ParameterizedTypeReference<Integer>() {
                });

        Integer response = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(0, response);
    }
    @Test
    @DisplayName("Update calendar ")
    public void test_update_calendar(){
        List<PriceDTO> dtos = new ArrayList<>();
        LocalDate now = LocalDate.now();
        dtos.add(new PriceDTO(now,now.plusDays(5),50));
        ResponseEntity<Integer> responseEntity = restTemplate.exchange("/api/accommodations/price/1",
                PUT,
                new HttpEntity<>(dtos,getHttpHeaders()),
                new ParameterizedTypeReference<Integer>() {
                });

        Integer response = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(6, response);

        List<Day> list =  accommodationService.findCalendar(1l);
        assertEquals(6,list.size());
    }

    @Test
    @DisplayName("Disable days - calendar doesn't exist")
    public void test_disable_days_calendar_does_not_exist(){
        LocalDate now = LocalDate.now();
        PriceDTO dto = new PriceDTO(now,now.plusDays(5),50);
        ResponseEntity<Integer> responseEntity = restTemplate.exchange("/api/accommodations/calendar/2",
                POST,
                new HttpEntity<>(dto,getHttpHeaders()),
                new ParameterizedTypeReference<Integer>() {
                });

        Integer response = responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(0, response);
    }

    @Test
    @DisplayName("Disable days - accommodationId is invalid")
    public void test_disable_days_accommodation_does_not_exist(){
        LocalDate now = LocalDate.now();
        PriceDTO dto = new PriceDTO(now,now.plusDays(5),50);
        ResponseEntity<Integer> responseEntity = restTemplate.exchange("/api/accommodations/calendar/15",
                POST,
                new HttpEntity<>(dto,getHttpHeaders()),
                new ParameterizedTypeReference<Integer>() {
                });

        Integer response = responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(0, response);
    }

    @Test
    @DisplayName("Disable days ")
    public void test_disable_days(){
        LocalDate now = LocalDate.now();
        PriceDTO dto = new PriceDTO(now.minusDays(1),now.plusDays(3),60);
        ResponseEntity<Integer> responseEntity = restTemplate.exchange("/api/accommodations/calendar/1",
                POST,
                new HttpEntity<>(dto,getHttpHeaders()),
                new ParameterizedTypeReference<Integer>() {
                });

        Integer response = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(5, response);
    }

    @Test
    @DisplayName("Get list with intevarls")
    public void test_getPricelist(){
        ResponseEntity<List<PriceDTO>> responseEntity = restTemplate.exchange("/api/accommodations/pricelists/1",
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                new ParameterizedTypeReference<List<PriceDTO>>() {
                });
        List<PriceDTO> response = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
}