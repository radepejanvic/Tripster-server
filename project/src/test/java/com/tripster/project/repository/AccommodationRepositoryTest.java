package com.tripster.project.repository;

import com.tripster.project.model.Day;
import com.tripster.project.model.enums.DayStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class AccommodationRepositoryTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Test
    public void test_return_priceList(){

        List<Day> list = accommodationRepository.findCalendar(1l, DayStatus.AVAILABLE);
        assertEquals(5,list.size());
    }


}