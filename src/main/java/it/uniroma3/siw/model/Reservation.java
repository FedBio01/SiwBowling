package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Reservation {
	
	public static final Integer TOTAL_BOWLING_ALLEY = 5;
   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private Integer numberOfPlayers;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate reservationDate;
	
	@OneToOne
	private BowlingMatch bowlingMatch;
	
	public Integer getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(Integer numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public BowlingMatch getBowlingMatch() {
		return bowlingMatch;
	}

	public void setBowlingMatch(BowlingMatch bowlingMatch) {
		this.bowlingMatch = bowlingMatch;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public LocalDate getReservationDate() {
		return reservationDate;
	}
	
	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bowlingMatch, numberOfPlayers, reservationDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(bowlingMatch, other.bowlingMatch)
				&& Objects.equals(numberOfPlayers, other.numberOfPlayers)
				&& Objects.equals(reservationDate, other.reservationDate);
	}
	
	
	
	




}