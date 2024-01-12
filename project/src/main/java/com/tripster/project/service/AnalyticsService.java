package com.tripster.project.service;

import com.tripster.project.dto.Analytics;
import com.tripster.project.dto.StatusAnalytics;
import com.tripster.project.model.enums.ReservationStatus;
import com.tripster.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Analytics> generateAnnualAnalytics(Long hostId, int year) {
        List<Analytics> analytics = new ArrayList<>();

        List<Object[]> raw = reservationRepository.calculateAnnualAnalytics(hostId, year);

        if(raw.isEmpty()) {
            return analytics;
        }

        Long currentId = (Long) raw.get(0)[0];
        Analytics analytic = new Analytics();

        for (Object[] o : raw) {
            long id = (long) o[0];

            if(id != currentId) {
                currentId = id;
                analytics.add(analytic);
                analytic = new Analytics();
            }

            mapStatsToAnnualAnalytics(analytic, o);
        }

        analytics.add(analytic);

        return analytics;
    }
    public List<Object[]> gen(Long hostId, int year) {

        return reservationRepository.calculateAnnualAnalytics(hostId, year);
    }

    private void mapStatsToAnnualAnalytics(Analytics analytic, Object[] stats) {
        int month = (int) stats[2];
        long count = (long) stats[4];
        double revenue = (double) stats[5];
        analytic.setAccommodationId((long) stats[0]);
        analytic.setName((String) stats[1]);
        analytic.reservationsPerMonth[month-1] = count;
        analytic.revenuePerMonth[month-1] = revenue;
    }

    public StatusAnalytics generateStatusAnalytics(Long hostId, long startMillis, long endMillis) {
        StatusAnalytics analytics = new StatusAnalytics();
        LocalDate start = Instant.ofEpochMilli(startMillis).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = Instant.ofEpochMilli(endMillis).atZone(ZoneId.systemDefault()).toLocalDate();

        List<Object[]> raw = reservationRepository.calculateTotalAnalyticsPerStatus(hostId, start, end);

        for (Object[] stats : raw) {
            mapStatsToStatusAnalytics(analytics, stats);
        }

        return analytics;
    }

    private void mapStatsToStatusAnalytics(StatusAnalytics analytic, Object[] stats) {
        ReservationStatus status = (ReservationStatus) stats[0];
        long count = (long) stats[1];
        double revenue = (double) stats[2];

        switch(status) {
            case PENDING:
                analytic.setPendingCount(count);
                analytic.setPendingRevenue(revenue);
                return;
            case ACCEPTED:
                analytic.setAcceptedCount(count);
                analytic.setAcceptedRevenue(revenue);
                return;
            case CANCELLED:
                analytic.setCancelledCount(count);
                analytic.setCancelledRevenue(revenue);
                return;
            case REJECTED:
                analytic.setRejectedCount(count);
                analytic.setRejectedRevenue(revenue);
        }
    }

    public List<Analytics> generateCustomPeriodAnalytics(Long hostId, long startMillis, long endMillis) {
        List<Analytics> analytics = new ArrayList<>();
        LocalDate start = Instant.ofEpochMilli(startMillis).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = Instant.ofEpochMilli(endMillis).atZone(ZoneId.systemDefault()).toLocalDate();

        List<Object[]> raw = reservationRepository.calculateTotalAnalyticsPerAccommodation(hostId, start, end);

        for (Object[] stats : raw) {
            analytics.add(mapStatsToTotalAnalytics(stats));
        }

        return analytics;
    }
    
    private Analytics mapStatsToTotalAnalytics(Object[] stats) {
        Analytics analytic = new Analytics();

        analytic.setAccommodationId((long) stats[0]);
        analytic.setName((String) stats[1]);
        analytic.setTotalReservations((long) stats[2]);
        analytic.setTotalRevenue((double) stats[3]);

        return analytic;
    }

}
