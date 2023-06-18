package it.uniroma3.siw.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.BowlingAlley;

public interface BowlingAlleyRepository extends CrudRepository<BowlingAlley, Long> {
	
	
	@Query(value="select * "
			+ "from bowling_alley b "
			+ "where b.id not in "
			+ "(select bowling_alley_id "
			+ "from reservation "
			+ "where reservation_date = :reservationDate "
			+ "and reservation_time = :reservationTime ) ", nativeQuery=true)
	public Iterable<BowlingAlley> findAlleysNotReserved(@Param("reservationDate") LocalDate reservationDate,
			@Param("reservationTime") String reservationTime);


	public boolean existsByAlleyNumber(Integer alleyNumber);

}