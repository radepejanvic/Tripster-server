package com.tripster.project.service;

import com.tripster.project.dto.PriceDTO;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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
            calendar.add(new Day(date, intervals.get(date), true));
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
    



}