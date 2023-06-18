package it.uniroma3.siw.repository;


import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Reservation;


public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	public boolean existsByReservationDateAndReservationTime(LocalDate reservationDate, String reservationTime);
	

	@Query(value="select * "
			+ "from reservation r "
			+ "where reservation_date = :reservationDate "
			+ " and reservation_time = :reservationTime ", nativeQuery=true)
	public Iterable<Reservation> findReservationsByDayAndTime(@Param("reservationDate") LocalDate reservationDate,
			@Param("reservationTime") String reservationTime);
	
	


}