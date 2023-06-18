package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Reservation;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ReservationRepository;


@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private UserService userService;
	
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
	
	public List<Reservation> findReservationsByUser(Long userId) {
		return this.reservationRepository.findReservationsByuserId(userId);
	}
	
	@Transactional
	public Reservation saveReservationToUser(Long userId, Long reservationId) {
		Reservation reservation = this.findReservationById(reservationId);
		User user = this.userService.getUser(userId);
		user.getReservations().add(reservation);
		reservation.setUser(user);
		return this.reservationRepository.save(reservation);
	}

	public void deleteReservation(Long reservationId) {
		this.reservationRepository.delete(this.findReservationById(reservationId));
		
	}
	
}
