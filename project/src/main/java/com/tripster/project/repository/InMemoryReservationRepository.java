package com.tripster.project.repository;

import com.tripster.project.model.Reservation;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryReservationRepository implements ReservationRepository {
    private static AtomicLong counter = new AtomicLong();

    private final ConcurrentMap<Long, Reservation> reservatoins = new ConcurrentHashMap<Long, Reservation>();

    @Override
    public Collection<Reservation> findAll() {
        return this.reservatoins.values();
    }

    @Override
    public Reservation create(Reservation reservation) {
        Long id = reservation.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            reservation.setId(id);
        }
        this.reservatoins.put(id, reservation);
        return reservation;
    }

    @Override
    public Reservation findOne(Long id) {
        return this.reservatoins.get(id);
    }

    @Override
    public Reservation update(Reservation reservation) {
        Long id = reservation.getId();
        if (id != null) {
            this.reservatoins.put(id, reservation);
        }
        return reservation;
    }

    @Override
    public void delete(Long id) {
        this.reservatoins.remove(id);
    }

}
