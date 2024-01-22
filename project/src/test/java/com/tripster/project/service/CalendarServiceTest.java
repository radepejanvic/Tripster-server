package com.tripster.project.service;

import com.tripster.project.dto.PriceDTO;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Address;
import com.tripster.project.model.Day;
import com.tripster.project.model.Host;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import com.tripster.project.model.enums.DayStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class CalendarServiceTest {

    private static final Long VALID_ACCOMMODATION=1L;
    private static final Long INVALID_ACCOMMODATION=0L;
    @MockBean
    private AccommodationService accommodationService;

    @Autowired
    private CalendarService calendarService;

    @Test
    public void test_getCalendar_one_interval(){
        List<PriceDTO> dtos = new ArrayList<>();
        LocalDate now = LocalDate.now();
        dtos.add(new PriceDTO(now,now.plusDays(4),50));
        Set<Day>  days = calendarService.getCalendar(dtos);
        List<Day> list = new ArrayList<>(days);
        list.sort(Comparator.comparing(Day::getDate));
        assertEquals(5,days.size());
        int i = 1;
        for (Day day: list){
            assertEquals(50,day.getPrice());
            LocalDate date = now.plusDays(i);
            i++;
            assertEquals(date,day.getDate());

        }
        verifyNoInteractions(accommodationService);
    }

    @ParameterizedTest
    @MethodSource(value = "source_dates_prices_nothing_to_order_more_intervals")
    public void test_getCalendar_more_interval(LocalDate date,Double price){
        List<PriceDTO> dtos = new ArrayList<>();
        LocalDate now = LocalDate.now();
        dtos.add(new PriceDTO(now,now.plusDays(4),50));
        dtos.add(new PriceDTO(now.plusDays(1),now.plusDays(2),60));
        Set<Day>  days = calendarService.getCalendar(dtos);

        assertEquals(5,days.size());
        boolean entered = false;
        for (Day priceList : days) {
            if(priceList.getDate().equals(date)){
                assertEquals(price, priceList.getPrice());
                entered = true;
                break;
            }
        }
        verifyNoInteractions(accommodationService);
    }
    @Test
    public void test_updateCalendar(){
        Accommodation accommodation = new Accommodation();
        accommodation.setId(VALID_ACCOMMODATION);
        Set<Day> days = new HashSet<>();
        LocalDate now = LocalDate.now();
        days.add(new Day(now,60, DayStatus.AVAILABLE));
        days.add(new Day(now.plusDays(1),60, DayStatus.AVAILABLE));
        days.add(new Day(now.plusDays(2),60, DayStatus.AVAILABLE));
        accommodation.setCalendar(days);

        List<PriceDTO> dtos = new ArrayList<>();
        dtos.add(new PriceDTO(now.minusDays(1),now.plusDays(1),50));

        when(accommodationService.findOne(1l)).thenReturn(accommodation);
        when(accommodationService.save(any(Accommodation.class))).thenReturn(accommodation);

        int size = calendarService.updateCalendar(1l,dtos);

        assertEquals(3,size);
        verify(accommodationService).findOne(1l);
        verify(accommodationService).save(accommodation);
        verifyNoMoreInteractions(accommodationService);
    }

    @Test
    public void test_disableDays_accommodation_does_not_exist(){
        Accommodation accommodation = new Accommodation();
        accommodation.setId(INVALID_ACCOMMODATION);
        LocalDate now = LocalDate.now();

        PriceDTO dto = new PriceDTO(now.minusDays(1),now.plusDays(1),50);

        when(accommodationService.findOne(INVALID_ACCOMMODATION)).thenThrow(RuntimeException.class);
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                ()->calendarService.disableDays(INVALID_ACCOMMODATION,dto));

        assertEquals("Accommodation with id not found",runtimeException.getMessage());
        verify(accommodationService).findOne(INVALID_ACCOMMODATION);
        verifyNoMoreInteractions(accommodationService);
    }
    @Test
    public void test_disableDays_calendar_does_not_exist(){
        Accommodation accommodation = new Accommodation();
        accommodation.setId(VALID_ACCOMMODATION);
        LocalDate now = LocalDate.now();
        Set<Day> days = new HashSet<>();
        accommodation.setCalendar(days);
        PriceDTO dto = new PriceDTO(now.minusDays(1),now.plusDays(1),50);

        when(accommodationService.findOne(VALID_ACCOMMODATION)).thenReturn(accommodation);
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                ()->calendarService.disableDays(VALID_ACCOMMODATION,dto));

        assertEquals("Calendar doesn't exist.",runtimeException.getMessage());
        verify(accommodationService).findOne(VALID_ACCOMMODATION);
        verifyNoMoreInteractions(accommodationService);
    }

    @Test
    public void test_disableDays() throws Exception {
        Accommodation accommodation = new Accommodation();
        accommodation.setId(VALID_ACCOMMODATION);
        LocalDate now = LocalDate.now();
        Set<Day> days = new HashSet<>();
        days.add(new Day(now,60, DayStatus.AVAILABLE));
        days.add(new Day(now.plusDays(1),60, DayStatus.AVAILABLE));
        days.add(new Day(now.plusDays(2),60, DayStatus.AVAILABLE));
        accommodation.setCalendar(days);
        PriceDTO dto = new PriceDTO(now.minusDays(1),now.plusDays(1),50);

        when(accommodationService.findOne(VALID_ACCOMMODATION)).thenReturn(accommodation);
        when(accommodationService.save(any(Accommodation.class))).thenReturn(accommodation);

        int size = calendarService.disableDays(1l,dto);

        assertEquals(3,size);
        verify(accommodationService).findOne(VALID_ACCOMMODATION);
        verify(accommodationService).save(accommodation);
    }

    @Test
    public void test_getPricelists_one_interval(){
        Accommodation accommodation = new Accommodation();
        accommodation.setId(VALID_ACCOMMODATION);
        LocalDate now = LocalDate.now();
        Set<Day> days = new HashSet<>();
        days.add(new Day(now,60, DayStatus.AVAILABLE));
        days.add(new Day(now.plusDays(1),60, DayStatus.AVAILABLE));
        days.add(new Day(now.plusDays(2),60, DayStatus.AVAILABLE));
        accommodation.setCalendar(days);
        List<Day> days1 = new ArrayList<>(days);
        days1.sort(Comparator.comparing(Day::getDate));
        when(accommodationService.findCalendar(VALID_ACCOMMODATION)).thenReturn(days1);

        List<PriceDTO> dtos = calendarService.getPricelists(VALID_ACCOMMODATION);
        assertEquals(1,dtos.size());
        assertEquals(now.plusDays(2),dtos.get(0).getEnd());
        assertEquals(now,dtos.get(0).getStart());
        assertEquals(60,dtos.get(0).getPrice());

        verify(accommodationService).findCalendar(VALID_ACCOMMODATION);
        verifyNoMoreInteractions(accommodationService);

    }

    @Test
    public void test_getPricelists_more_interval(){
        Accommodation accommodation = new Accommodation();
        accommodation.setId(VALID_ACCOMMODATION);
        LocalDate now = LocalDate.now();
        Set<Day> days = new HashSet<>();
        days.add(new Day(now,60, DayStatus.AVAILABLE));
        days.add(new Day(now.plusDays(1),60, DayStatus.AVAILABLE));
        days.add(new Day(now.plusDays(2),70, DayStatus.AVAILABLE));
        days.add(new Day(now.plusDays(3),70, DayStatus.AVAILABLE));
        accommodation.setCalendar(days);
        List<Day> days1 = new ArrayList<>(days);
        days1.sort(Comparator.comparing(Day::getDate));
        when(accommodationService.findCalendar(VALID_ACCOMMODATION)).thenReturn(days1);

        List<PriceDTO> dtos = calendarService.getPricelists(VALID_ACCOMMODATION);
        assertEquals(2,dtos.size());

        assertEquals(now.plusDays(1),dtos.get(0).getEnd());
        assertEquals(now,dtos.get(0).getStart());
        assertEquals(60,dtos.get(0).getPrice());

        assertEquals(now.plusDays(3),dtos.get(1).getEnd());
        assertEquals(now.plusDays(2),dtos.get(1).getStart());
        assertEquals(70,dtos.get(1).getPrice());


        verify(accommodationService).findCalendar(VALID_ACCOMMODATION);
        verifyNoMoreInteractions(accommodationService);

    }
    @Test
    @DisplayName("Reserve days - invalid dates")
    public void test_reserve_days_invalid_dates() {
        LocalDate end = LocalDate.now();
        LocalDate start = end.plusDays(10);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()->calendarService.reserveDays(1L, start, end));

        assertEquals("Start date must be before end date.", exception.getMessage());

        verifyNoInteractions(accommodationService);
    }

    @ParameterizedTest
    @DisplayName("Reserve days - non existent accommodation")
    @ValueSource(longs = {0, 100, 200})
    public void test_reserve_days_non_existent_accommodation(Long id) {
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(10);

        when(accommodationService.findOne(anyLong())).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()->calendarService.reserveDays(id, start, end));

        assertEquals("Accommodation with the given id not found.", exception.getMessage());

        verify(accommodationService).findOne(id);
        verifyNoMoreInteractions(accommodationService);
    }

    @Test
    @DisplayName("Reserve days - days match")
    public void test_reserve_days_days_match() {
        Set<Day> calendar = new HashSet<>();
        calendar.add(new Day(1L, LocalDate.of(2023, 1, 1), 100, DayStatus.AVAILABLE));
        calendar.add(new Day(2L, LocalDate.of(2023, 1, 2), 100, DayStatus.AVAILABLE));
        calendar.add(new Day(3L, LocalDate.of(2023, 1, 3), 100, DayStatus.AVAILABLE));
        calendar.add(new Day(4L, LocalDate.of(2023, 1, 4), 100, DayStatus.AVAILABLE));
        calendar.add(new Day(5L, LocalDate.of(2023, 1, 5), 100, DayStatus.AVAILABLE));

        Accommodation accommodation = new Accommodation();
        accommodation.setId(1L);
        accommodation.setCalendar(calendar);

        LocalDate start = LocalDate.of(2023, 1, 2);
        LocalDate end = LocalDate.of(2023, 1, 4);

        when(accommodationService.findOne(1L)).thenReturn(accommodation);
        when(accommodationService.save(accommodation)).thenReturn(accommodation);

        int reserved = calendarService.reserveDays(1L, start, end);

        assertEquals(reserved, 3);

        for (Day day : accommodation.getCalendar()) {
            if (!day.getDate().isBefore(start) && !day.getDate().isAfter(end)) {
                assertEquals(DayStatus.RESERVED, day.getAvailability());
            }
        }

        verify(accommodationService).findOne(1L);
        verify(accommodationService).save(accommodation);
    }

    @Test
    @DisplayName("Reserve days - days don`t match")
    public void test_reserve_days_days_dont_match() {
        Set<Day> calendar = new HashSet<>();
        calendar.add(new Day(1L, LocalDate.of(2023, 1, 1), 100, DayStatus.AVAILABLE));
        calendar.add(new Day(2L, LocalDate.of(2023, 1, 2), 100, DayStatus.AVAILABLE));
        calendar.add(new Day(3L, LocalDate.of(2023, 1, 3), 100, DayStatus.AVAILABLE));
        calendar.add(new Day(4L, LocalDate.of(2023, 1, 4), 100, DayStatus.AVAILABLE));
        calendar.add(new Day(5L, LocalDate.of(2023, 1, 5), 100, DayStatus.AVAILABLE));

        Accommodation accommodation = new Accommodation();
        accommodation.setId(1L);
        accommodation.setCalendar(calendar);

        LocalDate start = LocalDate.of(2023, 4, 2);
        LocalDate end = LocalDate.of(2023, 4, 4);

        when(accommodationService.findOne(1L)).thenReturn(accommodation);
        when(accommodationService.save(accommodation)).thenReturn(accommodation);

        int reserved = calendarService.reserveDays(1L, start, end);

        assertEquals(reserved, 0);

        for (Day day : accommodation.getCalendar()) {
            if (!day.getDate().isBefore(start) && !day.getDate().isAfter(end)) {
                assertEquals(DayStatus.RESERVED, day.getAvailability());
            }
        }

        verify(accommodationService).findOne(1L);
        verify(accommodationService).save(accommodation);
    }

    static Stream<Arguments> source_dates_prices_nothing_to_order_more_intervals() {
        return Stream.of(
                arguments(LocalDate.now().plusDays(1), 50.0),
                arguments(LocalDate.now().plusDays(2), 60.0),
                arguments(LocalDate.now().plusDays(3), 60.0),
                arguments(LocalDate.now().plusDays(5), 50.0),
                arguments(LocalDate.now().plusDays(4), 50.0)
        );
    }
}