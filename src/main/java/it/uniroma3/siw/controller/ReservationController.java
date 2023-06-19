package it.uniroma3.siw.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.ReservationValidator;
import it.uniroma3.siw.model.BowlingAlley;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Reservation;
import it.uniroma3.siw.service.BowlingAlleyService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.ReservationService;
import jakarta.validation.Valid;

@Controller
public class ReservationController {

	@Autowired 
	private ReservationService reservationService;
	
	@Autowired 
	private CredentialsService credentialsService;
	
	@Autowired 
	private BowlingAlleyService bowlingAlleyService;
	
	@Autowired 
	private ReservationValidator reservationValidator;;

	@GetMapping(value="/registeredUser/formNewReservation")
	public String formNewReservation(Model model) {
		model.addAttribute("reservation", new Reservation());
		return "registeredUser/formNewReservation.html";
	}
	
	@GetMapping(value="/admin/indexReservation")
	public String indexReservation() {
		return "admin/indexReservation.html";
	}
	
	@PostMapping("/registeredUser/reservation")
	public String newReservation(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult bindingResult, Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		this.reservationValidator.validate(reservation, bindingResult);
		if (!bindingResult.hasErrors()) {
			List<BowlingAlley> alleysNotReserved = this.bowlingAlleyService.alleysNotReserved(reservation.getReservationDate(), reservation.getReservationTime());
			Collections.shuffle(alleysNotReserved);
			BowlingAlley alleyNotReserved = alleysNotReserved.get(0);
			reservation.setBowlingAlley(alleyNotReserved);
			this.reservationService.saveReservation(reservation);
			this.reservationService.saveReservationToUser(credentials.getUser().getId(),reservation.getId()); 
			model.addAttribute("reservation", reservation);
			return "registeredUser/reservationSuccessful.html";
		} else {
			return "registeredUser/formNewReservation.html"; 
		}
	}

	@GetMapping("/reservation/{id}")
	public String getReservation(@PathVariable("id") Long id, Model model) {
		model.addAttribute("reservation", this.reservationService.findReservationById(id));
		return "reservation.html";
	}

	@GetMapping("/admin/reservations")
	public String getReservations(Model model) {
		model.addAttribute("reservations", this.reservationService.findAllReservations());
		return "admin/reservations.html";
	}
	
	@GetMapping("registeredUser/manageReservations")
	public String getReservationsByUser(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		model.addAttribute("reservations", this.reservationService.findReservationsByUser(credentials.getUser().getId()));
		return "registeredUser/manageReservations.html";
	}
	
	@GetMapping(value = "registeredUser/deleteReservation/{reservationId}")
	public String deleteReservation (Model model, @PathVariable("reservationId") Long reservationId) {
		this.reservationService.deleteReservation(reservationId);
		model.addAttribute("reservations",this.reservationService.findAllReservations());
		return "/registeredUser/manageReservations.html";
	}
	
	@GetMapping("/registeredUser/formUpdateReservation/{reservationId}")
	public String formUpdateReservation(Model model, @PathVariable("reservationId") Long reservationId) {
		model.addAttribute("reservation", this.reservationService.findReservationById(reservationId));
		return "/registeredUser/formUpdateReservation.html";
	}
	
	@PostMapping("/registeredUser/updateReservation/{reservationId}")
	public String updateReservation(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult bindingResult, Model model, @PathVariable("reservationId") Long reservationId) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		this.reservationValidator.validate(reservation, bindingResult);
		if (!bindingResult.hasErrors()) {
			List<BowlingAlley> alleysNotReserved = this.bowlingAlleyService.alleysNotReserved(reservation.getReservationDate(), reservation.getReservationTime());
			Collections.shuffle(alleysNotReserved);
			BowlingAlley alleyNotReserved = alleysNotReserved.get(0);
			reservation.setBowlingAlley(alleyNotReserved);
			Reservation realReservation = this.reservationService.findReservationById(reservationId);
			realReservation.setBowlingAlley(reservation.getBowlingAlley());
			realReservation.setNumberOfPlayers(reservation.getNumberOfPlayers());
			realReservation.setReservationDate(reservation.getReservationDate());
			realReservation.setReservationTime(reservation.getReservationTime());
			this.reservationService.saveReservation(realReservation);
			this.reservationService.saveReservationToUser(credentials.getUser().getId(),realReservation.getId()); 
			model.addAttribute("reservation", realReservation);
			return "registeredUser/reservationSuccessful.html";
		} else {
			return "registeredUser/formUpdateReservation.html"; 
		}
	}
}
