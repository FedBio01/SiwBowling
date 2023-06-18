package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class BowlingAlley {
    
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        
        @NotNull
        private Integer alleyNumber;
        
        @OneToMany(mappedBy="bowlingAlley")
        private List<Reservation> reservations;
        
        public BowlingAlley() {
        	reservations= new ArrayList<>();
        }

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

		public List<Reservation> getReservations() {
			return reservations;
		}

		public void setReservations(List<Reservation> reservations) {
			this.reservations = reservations;
		}

		@Override
		public int hashCode() {
			return Objects.hash(alleyNumber, reservations);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BowlingAlley other = (BowlingAlley) obj;
			return Objects.equals(alleyNumber, other.alleyNumber) && Objects.equals(reservations, other.reservations);
		}
}