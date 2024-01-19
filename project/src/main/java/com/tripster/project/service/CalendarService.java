package com.tripster.project.service;

import com.tripster.project.dto.PriceDTO;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Day;
import com.tripster.project.model.enums.DayStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CalendarService {

    @Autowired
    private AccommodationService accommodationService;

    private Map<LocalDate, Double> orderPricelists(List<PriceDTO> pricelists) {

        Map<LocalDate, Double> intervals = new HashMap<>();
        LocalDate date;
        LocalDate start;
        LocalDate end;

        for (int i = pricelists.size()-1; i >= 0; i--) {

            start = pricelists.get(i).getStart().plusDays(1);
            end = pricelists.get(i).getEnd().plusDays(1);
            Double price = pricelists.get(i).getPrice();

            while(!start.isAfter(end)) {
                date = start;
                if (!intervals.containsKey(date)) {
                    intervals.put(date, price);
                }
                start = start.plusDays(1);
            }
        }

        return intervals;
    }

    private Set<Day> convertToCalendar(Map<LocalDate, Double> intervals) {
        Set<Day> calendar = new HashSet<>();

        for(LocalDate date: intervals.keySet()) {
            calendar.add(new Day(date, intervals.get(date), DayStatus.AVAILABLE));
        }

        return calendar;
    }

    public Set<Day> getCalendar(List<PriceDTO> pricelists) {
        return convertToCalendar(orderPricelists(pricelists));
    }

    public int updateCalendar(Long id, List<PriceDTO> pricelists) {
        Accommodation accommodation = accommodationService.findOne(id);
        Map<LocalDate, Double> intervals = orderPricelists(pricelists);

        LocalDate date;

        Set<Day> calendar = accommodation.getCalendar();

        for (Day day : calendar) {

            date = day.getDate();

            if (day.isAvailable() && intervals.containsKey(date)) {
                day.setPrice(intervals.get(date));
                intervals.remove(date);
            }

        }

        calendar.addAll(convertToCalendar(intervals));
        accommodation.setCalendar(calendar);
        accommodationService.save(accommodation);

        return calendar.size();
    }

    public List<PriceDTO> getPricelists(Long accommodationId) {

        List<PriceDTO> pricelists = new ArrayList<>();
        List<Day> calendar = accommodationService.findCalendar(accommodationId);

        Day current = null;
        Day last =  calendar.get(0);

        LocalDate start = last.getDate();

        for(int i = 1; i < calendar.size(); i++) {

            current = calendar.get(i);

            if (!current.getDate().isEqual(last.getDate().plusDays(1)) || current.getPrice() != last.getPrice())  {
                pricelists.add(new PriceDTO(start, last.getDate(), last.getPrice()));
                start = current.getDate();
            }

            last = current;
        }

        pricelists.add(new PriceDTO(
                start,
                current != null ? current.getDate() : last.getDate(),
                current.getPrice()));

        return pricelists;
    }

    public int disableDays(Long id, PriceDTO interval) throws Exception {
        interval.setStart(interval.getStart().plusDays(1));
        interval.setEnd(interval.getStart().plusDays(1));
        Accommodation accommodation = accommodationService.findOne(id);
        if (accommodation == null){
            throw new RuntimeException("Accommodation with id not found");
        }
        Set<Day> calendar = accommodation.getCalendar();

        if (calendar.size() == 0){
            throw new  RuntimeException("Calendar doesn't exist.");
        }
        int disabled = 0;

        for(Day day : calendar) {
            if (!day.getDate().isBefore(interval.getStart()) && !day.getDate().isAfter(interval.getEnd()) && day.isAvailable()) {
                day.setAvailability(DayStatus.NOT_AVAILABLE);
                disabled++;
            }
        }

        accommodation.setCalendar(calendar);
        accommodationService.save(accommodation);
        return disabled;
    }

    public int reserveDays(Long id, LocalDate start, LocalDate end) {
        start = start.plusDays(1);
        end = end.plusDays(1);
        Accommodation accommodation = accommodationService.findOne(id);
        Set<Day> calendar = accommodation.getCalendar();
        int reserved = 0;

        for(Day day : calendar) {
            if (!day.getDate().isBefore(start) && !day.getDate().isAfter(end) && day.isAvailable()) {
                day.setAvailability(DayStatus.RESERVED);
                reserved++;
            }
        }

        accommodation.setCalendar(calendar);
        accommodationService.save(accommodation);
        return reserved;
    }

    public int unreserveDays(Long id, LocalDate start, LocalDate end) {
        start = start.plusDays(1);
        end = end.plusDays(1);
        Accommodation accommodation = accommodationService.findOne(id);
        Set<Day> calendar = accommodation.getCalendar();
        int unreserved = 0;

        for(Day day : calendar) {
            if (!day.getDate().isBefore(start) && !day.getDate().isAfter(end) && !day.isAvailable()) {
                day.setAvailability(DayStatus.AVAILABLE);
                unreserved++;
            }
        }

        accommodation.setCalendar(calendar);
        accommodationService.save(accommodation);
        return unreserved;
    }

}
