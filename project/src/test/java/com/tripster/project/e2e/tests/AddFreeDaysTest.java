package com.tripster.project.e2e.tests;

import com.tripster.project.e2e.pages.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddFreeDaysTest extends TestBase{

    private  static final String USERNAME = "host@hotmail.com";
    private  static final String PASSWORD = "admin";
    private  static final String NAME = "Test hotel41";
    private  static final String SHORT_DESCRIPTION = "This is good hotel.";
    private  static final String MIN_CAP = "2";
    private  static final String MAX_CAP = "5";
    private static final String ROOM_TYPE = "ROOM";
    private static final String COUNTRY = "Serbia";
    private static final String CITY = "Novi Sad";
    private static final String STREET = "Pavla Papa";
    private static final String ZIP_CODE = "21000";
    private static final String STREET_NUMBER = "12";
    private static final String DESCRIPTION = "hsdgsghd";

    @Test
    void test_add_days(){

        HomePage homePage = new HomePage(driver);
        homePage.login(USERNAME,PASSWORD);
        homePage.openAddAccommodationPage();

        AddAccommodationPage addAccommodationPage = new AddAccommodationPage(driver);
        assertTrue(addAccommodationPage.isPageOpened());
        addAccommodationPage.setText(NAME,SHORT_DESCRIPTION,MIN_CAP,MAX_CAP);
        addAccommodationPage.setRoomTypeAndReservationType(ROOM_TYPE);
        addAccommodationPage.setAddressAndDescription(COUNTRY,CITY,STREET,ZIP_CODE,STREET_NUMBER,DESCRIPTION);
        addAccommodationPage.setPolicyAndCancelDuration(true,30);
        addAccommodationPage.scrollToBottom();
        addAccommodationPage.submit();

        PhotoPage photoPage = new PhotoPage(driver);
        assertTrue(photoPage.isPageOpened());
        List<String> list = new ArrayList<>();
        list.add("C:\\Users\\Nikola\\Desktop\\ISS\\project\\src\\main\\resources\\photos\\1_primary_1.jpg");
        list.add("C:\\Users\\Nikola\\Desktop\\ISS\\project\\src\\main\\resources\\photos\\1_primary_1.jpg");
        list.add("C:\\Users\\Nikola\\Desktop\\ISS\\project\\src\\main\\resources\\photos\\1_primary_1.jpg");
        list.add("C:\\Users\\Nikola\\Desktop\\ISS\\project\\src\\main\\resources\\photos\\1_primary_1.jpg");
        list.add("C:\\Users\\Nikola\\Desktop\\ISS\\project\\src\\main\\resources\\photos\\1_primary_1.jpg");
        photoPage.setPhotos(list);

        DatePage datePage  = new DatePage(driver);
        assertTrue(datePage.isPageOpened());
        datePage.addDate("January 26, 2024","January 28, 2024",50);
        datePage.addDate("January 27, 2024","January 30, 2024",60);
        datePage.addDate("January 26, 2024","January 29, 2024",80);
        datePage.remove(1);
        datePage.submit();
        datePage.finish();

        AccommodationInfoPage accommodationInfoPage = new AccommodationInfoPage(driver);
        assertTrue(accommodationInfoPage.isPageOpened(NAME));
        assertTrue(accommodationInfoPage.scrollToTop());
        accommodationInfoPage.mangedClick();

        UpdateAccommodationPage updateAccommodationPage = new UpdateAccommodationPage(driver);
        assertTrue(updateAccommodationPage.isPageOpened());
        assertTrue(updateAccommodationPage.scrollToBottom());
        updateAccommodationPage.addDate("January 26, 2024","January 30, 2024",100);
        updateAccommodationPage.submit();
        assertTrue(updateAccommodationPage.isValidNumberOfDateInterval(1));

        updateAccommodationPage.disableDays("January 27, 2024","January 28, 2024");
        assertTrue(updateAccommodationPage.isValidNumberOfDateInterval(2));    }


}
