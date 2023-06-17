package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.service.ReviewService;

@Controller
public class ReviewController {
	
	@Autowired 
	private ReviewService reviewService;
	
	@GetMapping(value="/registeredUser/formNewReview")
	public String formNewReview(Model model) {
		model.addAttribute("review", new Review());
		return "registeredUser/formNewReview.html";
	}
	
}
