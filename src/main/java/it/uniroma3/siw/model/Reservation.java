package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Reservation {
	
	
   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@NotNull
	@Min(1)
	@Max(10)
	private Integer numberOfPlayers;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate reservationDate;
	
	private String reservationTime;
	
	@ManyToOne
	private BowlingAlley bowlingAlley;
	
	public String getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}
	
	public Integer getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(Integer numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public BowlingAlley getBowlingAlley() {
		return bowlingAlley;
	}

	public void setBowlingAlley(BowlingAlley bowlingAlley) {
		this.bowlingAlley = bowlingAlley;
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
		return Objects.hash(bowlingAlley, numberOfPlayers, reservationDate, reservationTime);
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
				&& Objects.equals(reservationDate, other.reservationDate)
				&& Objects.equals(reservationTime, other.reservationTime);
	}

}