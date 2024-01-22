package com.tripster.project.e2e.tests;

import com.tripster.project.e2e.pages.GuestReservationsPage;
import com.tripster.project.e2e.pages.HomePage;
import com.tripster.project.e2e.pages.HostReservationsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcceptReservationTest extends TestBase {

    private  static final String USERNAME = "testHost@gmail.com";
    private  static final String PASSWORD = "admin";
    private  static final String ACCOMMODATION_NAME = "Gleichner, Toy and Stracke";
    private  static final String TIMESTAMP = " 10.02.2025. - 20.02.2025. ";
    private  static final String ACCEPTED = "ACCEPTED";
    private  static final String PENDING = "PENDING";
    private  static final String REJECTED = "REJECTED";
    private  static final int OVERLAPPING = 5;

    @Test
    @DisplayName("Accept reservation")
    void test_accept_reservation() {
        HomePage homePage = new HomePage(driver);
        homePage.login(USERNAME,PASSWORD);
        homePage.openHostReservationsPage();

        HostReservationsPage hostReservationsPage = new HostReservationsPage(driver);

        assertTrue(hostReservationsPage.isPageOpened());
        int pending = hostReservationsPage.countByStatus(PENDING);
        int accepted = hostReservationsPage.countByStatus(ACCEPTED);
        int rejected = hostReservationsPage.countByStatus(REJECTED);

        hostReservationsPage.findReservation(ACCOMMODATION_NAME, TIMESTAMP);
        hostReservationsPage.acceptReservation();
        hostReservationsPage.findReservation(ACCOMMODATION_NAME, TIMESTAMP);

        assertTrue(hostReservationsPage.compareStatus(ACCEPTED, ACCOMMODATION_NAME, TIMESTAMP));
        assertEquals(accepted + 1, hostReservationsPage.countByStatus(ACCEPTED));
        assertEquals(pending - OVERLAPPING, hostReservationsPage.countByStatus(PENDING));
        assertEquals(rejected + OVERLAPPING - 1, hostReservationsPage.countByStatus(REJECTED));
    }
}
