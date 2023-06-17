package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Reservation {
	
	public static final Integer TOTAL_BOWLING_ALLEY = 5;
   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Min(1)
	@Max(10)
	private Integer numberOfPlayers;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH")
	private LocalDateTime reservationDate;
	
	@DateTimeFormat(pattern = "HH")
	private LocalTime reservationTime;
	
	public LocalTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	@OneToOne
	private BowlingAlley bowlingAlley;
	
	public Integer getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(Integer numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public BowlingAlley getBowlingMatch() {
		return bowlingAlley;
	}

	public void setBowlingMatch(BowlingAlley bowlingMatch) {
		this.bowlingAlley = bowlingMatch;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public LocalDateTime getReservationDate() {
		return reservationDate;
	}
	
	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bowlingAlley, numberOfPlayers, reservationDate);
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
		return Objects.equals(bowlingAlley, other.bowlingAlley)
				&& Objects.equals(numberOfPlayers, other.numberOfPlayers)
				&& Objects.equals(reservationDate, other.reservationDate);
	}

}