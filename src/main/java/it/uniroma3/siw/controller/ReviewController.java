package it.uniroma3.siw.controller;


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

import it.uniroma3.siw.controller.validator.ReviewValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.ReviewService;
import jakarta.validation.Valid;

@Controller
public class ReviewController {
	
	@Autowired 
	private ReviewService reviewService;
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private ReviewValidator reviewValidator;
	
	@GetMapping(value="/registeredUser/formNewReview")
	public String formNewReview(Model model) {
		model.addAttribute("review", new Review());
		return "registeredUser/formNewReview.html";
		
	}
	
	@PostMapping("/registeredUser/review")
	public String newReview(@Valid @ModelAttribute("review") Review review, BindingResult bindingResult, Model model) {
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(user.getUsername());
		this.reviewValidator.validate(review, bindingResult);
		if (!bindingResult.hasErrors()) {
			Review createdReview=this.reviewService.createNewReview(review);
			this.reviewService.saveReviewToUser(credentials.getUser().getId(), review.getId());
			model.addAttribute(createdReview);
			return "registeredUser/reviewSuccessful.html";
		} else {
			return "registeredUser/formNewReview.html";
		}
	}
	
	@GetMapping("/reviews")
	public String reviews(Model model) {
		model.addAttribute("reviews", this.reviewService.findAllReviews());
		return "/reviews";
	}
	
	@GetMapping("/admin/reviewsToDelete")
	public String reviewsToDelete(Model model) {
		model.addAttribute("reviews", this.reviewService.findAllReviews());
		return "/admin/reviewsToDelete";
	}
	
	@GetMapping(value = "/deleteReview/{reviewId}")
	public String deleteReview (Model model, @PathVariable("reviewId") Long reviewId) {
		this.reviewService.deleteReview(reviewId);
		model.addAttribute("reviews",this.reviewService.findAllReviews());
		return "/admin/reviewsToDelete.html";
	}
	
}
