package com.tripster.project.service;

import com.tripster.project.dto.Analytics;
import com.tripster.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Analytics> generateAnnualAnalytics(Long hostId, int year) {
        List<Object[]> raw = reservationRepository.calculateAnnualAnalytics(hostId, year);
        List<Analytics> analytics = new ArrayList<>();

        Long currentId = (Long) raw.get(0)[0];
        Analytics analytic = new Analytics();

        for (Object[] o : raw) {
            long id = (long) o[0];

            if(id != currentId) {
                currentId = id;
                analytics.add(analytic);
                analytic = new Analytics();
            }

            setStats(analytic, o);
        }

        analytics.add(analytic);

        return analytics;
    }
    public List<Object[]> gen(Long hostId, int year) {

        return reservationRepository.calculateAnnualAnalytics(hostId, year);
    }

    private void setStats(Analytics analytic, Object[] stats) {
        int month = (int) stats[1];
        long count = (long) stats[3];
        double revenue = (double) stats[4];
        analytic.reservationsPerMonth[month-1] = count;
        analytic.revenuePerMonth[month-1] = revenue;
    }

}
