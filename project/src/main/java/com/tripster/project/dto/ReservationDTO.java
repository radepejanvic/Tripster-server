package com.tripster.project.dto;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Guest;
import com.tripster.project.model.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ReservationDTO {
    //Reservation info
    private Long id;
    private LocalDate start;
    private LocalDate end;
    private int duration;
    private int guestsNo;
    private double price;
    private ReservationStatus status;

    //Guest info
    private Long guestUserId;
    private String guestUserEmail;
    private String guestUserPassword;
    private UserType guestUserType;
    private UserStatus guestUserStatus;
    private String guestUserPhone;

    //Basic accommodation info
    private Long accmId;
    private String accmName;
    //Accommodation owner info
    private String accmOwnerEmail;
    private String accmOwnerName;
    private String accmOwnerSurname;
    private String accmOwnerPhone;

    //Accommodation details info
    private List<Ammenity> amenities;
    private File photo;
    private int minCap;
    private int maxCap;
    private int cancelDuration;
    @Enumerated(EnumType.ORDINAL)
    private AccommodationType accommodationType;
    private AccommodationStatus accommodationStatus;
}
