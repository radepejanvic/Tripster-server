package com.tripster.project.controller;

import com.tripster.project.dto.PriceDTO;
import com.tripster.project.dto.TokenDTO;
import com.tripster.project.dto.UserDTO;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Day;
import com.tripster.project.model.Reservation;
import com.tripster.project.model.enums.DayStatus;
import com.tripster.project.model.enums.ReservationStatus;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.CalendarService;
import com.tripster.project.service.ReservationServiceImpl;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ReservationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private AccommodationService accommodationService;

    private String accessToken;

    @BeforeEach
    public void login() {
        UserDTO dto = new UserDTO();
        dto.setEmail("host@hotmail.com");
        dto.setPassword("host");
        ResponseEntity<TokenDTO> responseEntity = restTemplate.exchange("/api/login",
                POST,
                new HttpEntity<>(dto),
                new ParameterizedTypeReference<>() {
                });
        this.accessToken = "Bearer " + responseEntity.getBody().getToken();
    }

    @BeforeEach
    public void calendarSetup() {
        List<PriceDTO> calendar = new ArrayList<>();
        calendar.add(new PriceDTO(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 2, 28), 100));

        Accommodation accommodation = accommodationService.findOne(1L);
        accommodation.setCalendar(calendarService.getCalendar(calendar));

        accommodationService.save(accommodation);
    }

    private HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        return headers;
    }

    @Test
    @DisplayName("Accept reservation - reservation not found")
    void test_accept_reservation_not_found() {
        String url = "/api/reservations/accept/" + 100;

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url,
                PUT,
                new HttpEntity<>(null, getHttpHeaders()),
                new ParameterizedTypeReference<>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    // Reservation with id = 1
    @Test
    @DisplayName("Accept reservation - no overlapping reservations")
    void test_accept_reservation_no_overlapping_reservations() {
        long id = 1L;
        String url = "/api/reservations/accept/" + id;

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url,
                PUT,
                new HttpEntity<>(null, getHttpHeaders()),
                new ParameterizedTypeReference<>() {
                });

        assertEquals(true, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ReservationStatus.ACCEPTED, reservationService.findOne(id).getStatus());
    }

    // Reservation with id = 7
    @Test
    @DisplayName("Accept reservation - with overlapping reservations")
    void test_accept_reservation_with_overlapping_reservations() {
        long id = 7L;
        String url = "/api/reservations/accept/" + id;

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url,
                PUT,
                new HttpEntity<>(null, getHttpHeaders()),
                new ParameterizedTypeReference<>() {
                });

        assertEquals(true, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ReservationStatus.ACCEPTED, reservationService.findOne(id).getStatus());
        assertEquals(ReservationStatus.REJECTED, reservationService.findOne(8L).getStatus());
        assertEquals(ReservationStatus.REJECTED, reservationService.findOne(9L).getStatus());
        assertEquals(ReservationStatus.REJECTED, reservationService.findOne(10L).getStatus());
        assertEquals(ReservationStatus.REJECTED, reservationService.findOne(11L).getStatus());
    }

}