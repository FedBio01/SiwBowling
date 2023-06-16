package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String name;
	private String surname;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String urlOfPicture;
	
	@ManyToMany(mappedBy="actors")
	private Set<BowlingAlley> starredMovies;
	
	@OneToMany(mappedBy="director")
	private List<BowlingAlley> directedMovies;
	
	public Reservation(){
		this.starredMovies = new HashSet<>();
		this.directedMovies = new LinkedList<>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getUrlOfPicture() {
		return urlOfPicture;
	}
	
	public void setUrlOfPicture(String urlOfPicture) {
		this.urlOfPicture = urlOfPicture;
	}
	
	public Set<BowlingAlley> getActorOf() {
		return starredMovies;
	}

	public void setActorOf(Set<BowlingAlley> starredMovies) {
		this.starredMovies = starredMovies;
	}

	public List<BowlingAlley> getDirectorOf() {
		return directedMovies;
	}

	public void setDirectorOf(List<BowlingAlley> directedMovies) {
		this.directedMovies = directedMovies;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, surname);
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
		return Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}

}