package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Reservation;
import it.uniroma3.siw.repository.ReservationRepository;


@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public boolean existsByDateAndTime (LocalDate reservationDate, String reservationTime) {
		return this.reservationRepository.existsByReservationDateAndReservationTime(reservationDate, reservationTime);
	}
	
	public List<Reservation> findReservationsByDateAndTime (LocalDate reservationDate, String reservationTime){
		return (List<Reservation>) this.reservationRepository.findReservationsByDayAndTime(reservationDate, reservationTime);
	}

	public void saveReservation( Reservation reservation) {
		this.reservationRepository.save(reservation);
	}

	public Reservation findReservationById(Long id) {
		return this.reservationRepository.findById(id).get();
	}

	public Iterable<Reservation> findAllReservations() {
		return this.reservationRepository.findAll();
	}
	
}
