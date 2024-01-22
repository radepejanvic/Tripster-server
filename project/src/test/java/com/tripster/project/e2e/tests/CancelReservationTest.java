package com.tripster.project.e2e.tests;

import com.tripster.project.e2e.pages.GuestReservationsPage;
import com.tripster.project.e2e.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CancelReservationTest extends TestBase {

    private  static final String USERNAME = "test@gmail.com";
    private  static final String PASSWORD = "admin";
    private  static final String ACCOMMODATION_NAME = "Reilly-Volkman";
    private  static final String TIMESTAMP = " 22.07.2025. - 26.07.2025. ";
    private  static final String STATUS = "CANCELLED";

    @Test
    @DisplayName("Cancel reservation")
    void test_cancel_reservation() {
        HomePage homePage = new HomePage(driver);
        homePage.login(USERNAME,PASSWORD);
        homePage.openReservationsPage();

        GuestReservationsPage guestReservationsPage = new GuestReservationsPage(driver);

        assertTrue(guestReservationsPage.isPageOpened());

        guestReservationsPage.findReservation(ACCOMMODATION_NAME, TIMESTAMP);
        guestReservationsPage.cancelReservation();
        guestReservationsPage.findReservation(ACCOMMODATION_NAME, TIMESTAMP);

        assertTrue(guestReservationsPage.compareStatus(STATUS, ACCOMMODATION_NAME, TIMESTAMP));
    }

}
