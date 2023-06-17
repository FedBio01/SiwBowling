package it.uniroma3.siw.repository;


import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	public boolean existsByReservationDate(LocalDateTime reservationDate);	

	@Query(value="select * "
			+ "from reservation r "
			+ "where r.id not in "
			+ "(select actors_id "
			+ "from movie_actors "
			+ "where movie_actors.starred_movies_id = :movieId)", nativeQuery=true)
	public Iterable<Reservation> findActorsNotInMovie(@Param("movieId") Long id);


}