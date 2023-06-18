package it.uniroma3.siw.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.validator.BowlingAlleyValidator;
import it.uniroma3.siw.model.Reservation;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.BowlingAlley;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.ReservationRepository;
import it.uniroma3.siw.service.BowlingAlleyService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.repository.BowlingAlleyRepository;
import jakarta.validation.Valid;

@Controller
public class BowlingAlleyController {
	
	@Autowired
	private BowlingAlleyService bowlingAlleyService;
	
	@Autowired
	private BowlingAlleyValidator bowlingAlleyValidator;

	@GetMapping("/admin/newBowlingAlley")
	public String formNewBowlingAlley(Model model) {
		model.addAttribute("bowlingAlley", new BowlingAlley());
		return "admin/formNewBowlingAlley.html";
	}
	
	@PostMapping("/admin/bowlingAlley")
	public String NewBowlingAlley(@Valid @ModelAttribute("bowlingAlley") BowlingAlley bowlingAlley, BindingResult bindingResult, Model model) {
		this.bowlingAlleyValidator.validate(bowlingAlley, bindingResult);
		if(!bindingResult.hasErrors()) {
			BowlingAlley alley =this.bowlingAlleyService.createNewAlley(bowlingAlley);
			model.addAttribute("bowlingAlley", alley);
			return "admin/bowlingAlleySuccessful.html";
		}
		else {
			return "admin/formNewBowlingAlley.html";
		}
		
	}
}

