package com.tripster.project.service;

import com.tripster.project.model.Reservation;
import com.tripster.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    public Reservation findOne(Long id) {
        Reservation res = reservationRepository.findById(id).orElseGet(null);
        return res;
    }
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
    public Page<Reservation> findAll(Pageable page ) {
        return reservationRepository.findAll(page);
    }
    public Reservation save(Reservation reservationDTO) {
        //Reservation res = ReservationDTOMapper.fromDTOtoReservation(reservationDTO);
        Reservation res = reservationRepository.save(reservationDTO);
        return res; //ReservationDTOMapper.fromReservationToDTO(res);
    }
    public void remove(Long id) {
        reservationRepository.deleteById(id);
    }
}
