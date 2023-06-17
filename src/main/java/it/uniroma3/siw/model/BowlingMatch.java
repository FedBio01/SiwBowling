package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class BowlingMatch {
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        
        @NotNull
        @Min(1)
        @Max(5)
        private Integer alleyNumber;
        
        @OneToOne
        private Reservation reservation;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Integer getAlleyNumber() {
			return alleyNumber;
		}

		public void setAlleyNumber(Integer alleyNumber) {
			this.alleyNumber = alleyNumber;
		}

		public Reservation getReservation() {
			return reservation;
		}

		public void setReservation(Reservation reservation) {
			this.reservation = reservation;
		}

		@Override
		public int hashCode() {
			return Objects.hash(alleyNumber, reservation);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BowlingMatch other = (BowlingMatch) obj;
			return Objects.equals(alleyNumber, other.alleyNumber) && Objects.equals(reservation, other.reservation);
		}
}