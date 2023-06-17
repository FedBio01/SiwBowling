package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.validator.BowlingAlleyValidator;
import it.uniroma3.siw.model.Reservation;
import it.uniroma3.siw.model.BowlingMatch;
import it.uniroma3.siw.repository.ReservationRepository;
import it.uniroma3.siw.repository.BowlingAlleyRepository;
import jakarta.validation.Valid;

@Controller
public class BowlingMatchController {
	@Autowired 
	private BowlingAlleyRepository bowlingAlleyRepository;
	
	@Autowired 
	private ReservationRepository reservationRepository;

	@Autowired 
	private BowlingAlleyValidator bowlingAlleyValidator;

	@GetMapping(value="/admin/formNewMovie")
	public String formNewMovie(Model model) {
		model.addAttribute("movie", new BowlingMatch());
		return "admin/formNewMovie.html";
	}

	@GetMapping(value="/admin/formUpdateMovie/{id}")
	public String formUpdateMovie(@PathVariable("id") Long id, Model model) {
		model.addAttribute("movie", bowlingAlleyRepository.findById(id).get());
		return "admin/formUpdateMovie.html";
	}

	@GetMapping(value="/admin/indexMovie")
	public String indexMovie() {
		return "admin/indexMovie.html";
	}
	
	@GetMapping(value="/admin/manageMovies")
	public String manageMovies(Model model) {
		model.addAttribute("movies", this.bowlingAlleyRepository.findAll());
		return "admin/manageMovies.html";
	}
	
	@GetMapping(value="/admin/setDirectorToMovie/{directorId}/{movieId}")
	public String setDirectorToMovie(@PathVariable("directorId") Long directorId, @PathVariable("movieId") Long movieId, Model model) {
		
		Reservation director = this.reservationRepository.findById(directorId).get();
		BowlingMatch movie = this.bowlingAlleyRepository.findById(movieId).get();
		movie.setDirector(director);
		this.bowlingAlleyRepository.save(movie);
		
		model.addAttribute("movie", movie);
		return "admin/formUpdateMovie.html";
	}
	
	
	@GetMapping(value="/admin/addDirector/{id}")
	public String addDirector(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artists", reservationRepository.findAll());
		model.addAttribute("movie", bowlingAlleyRepository.findById(id).get());
		return "admin/directorsToAdd.html";
	}

	@PostMapping("/admin/movie")
	public String newMovie(@Valid @ModelAttribute("movie") BowlingMatch movie, BindingResult bindingResult, Model model) {
		
		this.bowlingAlleyValidator.validate(movie, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.bowlingAlleyRepository.save(movie); 
			model.addAttribute("movie", movie);
			return "movie.html";
		} else {
			return "admin/formNewMovie.html"; 
		}
	}

	@GetMapping("/movie/{id}")
	public String getMovie(@PathVariable("id") Long id, Model model) {
		model.addAttribute("movie", this.bowlingAlleyRepository.findById(id).get());
		return "movie.html";
	}

	@GetMapping("/movie")
	public String getMovies(Model model) {		
		model.addAttribute("movies", this.bowlingAlleyRepository.findAll());
		return "movies.html";
	}
	
	@GetMapping("/formSearchMovies")
	public String formSearchMovies() {
		return "formSearchMovies.html";
	}

	@PostMapping("/searchMovies")
	public String searchMovies(Model model, @RequestParam int year) {
		model.addAttribute("movies", this.bowlingAlleyRepository.findByYear(year));
		return "foundMovies.html";
	}
	
	@GetMapping("/admin/updateActors/{id}")
	public String updateActors(@PathVariable("id") Long id, Model model) {

		List<Reservation> actorsToAdd = this.actorsToAdd(id);
		model.addAttribute("actorsToAdd", actorsToAdd);
		model.addAttribute("movie", this.bowlingAlleyRepository.findById(id).get());

		return "admin/actorsToAdd.html";
	}

	@GetMapping(value="/admin/addActorToMovie/{actorId}/{movieId}")
	public String addActorToMovie(@PathVariable("actorId") Long actorId, @PathVariable("movieId") Long movieId, Model model) {
		BowlingMatch movie = this.bowlingAlleyRepository.findById(movieId).get();
		Reservation actor = this.reservationRepository.findById(actorId).get();
		Set<Reservation> actors = movie.getActors();
		actors.add(actor);
		this.bowlingAlleyRepository.save(movie);
		
		List<Reservation> actorsToAdd = actorsToAdd(movieId);
		
		model.addAttribute("movie", movie);
		model.addAttribute("actorsToAdd", actorsToAdd);

		return "admin/actorsToAdd.html";
	}
	
	@GetMapping(value="/admin/removeActorFromMovie/{actorId}/{movieId}")
	public String removeActorFromMovie(@PathVariable("actorId") Long actorId, @PathVariable("movieId") Long movieId, Model model) {
		BowlingMatch movie = this.bowlingAlleyRepository.findById(movieId).get();
		Reservation actor = this.reservationRepository.findById(actorId).get();
		Set<Reservation> actors = movie.getActors();
		actors.remove(actor);
		this.bowlingAlleyRepository.save(movie);

		List<Reservation> actorsToAdd = actorsToAdd(movieId);
		
		model.addAttribute("movie", movie);
		model.addAttribute("actorsToAdd", actorsToAdd);

		return "admin/actorsToAdd.html";
	}

	private List<Reservation> actorsToAdd(Long movieId) {
		List<Reservation> actorsToAdd = new ArrayList<>();

		for (Reservation a : reservationRepository.findActorsNotInMovie(movieId)) {
			actorsToAdd.add(a);
		}
		return actorsToAdd;
	}
}
