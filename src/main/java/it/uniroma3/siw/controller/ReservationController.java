package it.uniroma3.siw.controller;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.ReservationValidator;
import it.uniroma3.siw.model.BowlingAlley;
import it.uniroma3.siw.model.Reservation;
import it.uniroma3.siw.service.BowlingAlleyService;
import it.uniroma3.siw.service.ReservationService;
import jakarta.validation.Valid;

@Controller
public class ReservationController {

	@Autowired 
	private ReservationService reservationService;
	
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
		this.reservationValidator.validate(reservation, bindingResult);
		if (!bindingResult.hasErrors()) {
			List<BowlingAlley> alleysNotReserved = this.bowlingAlleyService.alleysNotReserved(reservation.getReservationDate(), reservation.getReservationTime());
			Collections.shuffle(alleysNotReserved);
			BowlingAlley alleyNotReserved = alleysNotReserved.get(0);
			reservation.setBowlingAlley(alleyNotReserved);
			this.reservationService.saveReservation(reservation); 
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

	@GetMapping("/reservations")
	public String getReservations(Model model) {
		model.addAttribute("reservations", this.reservationService.findAllReservations());
		return "reservations.html";
	}
}
