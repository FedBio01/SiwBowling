package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
    protected ReviewRepository reviewRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public Review createNewReview(Review review) {
		return this.reviewRepository.save(review);
	}
	
	public List<Review> findAllReviews(){
		return (List<Review>) this.reviewRepository.findAll();
	}
	
	public Review findReviewById(Long id) {
		return this.reviewRepository.findById(id).get();
	}
	
	public Review saveReviewToUser(Long userId, Long reviewId) {
		Review review = this.findReviewById(reviewId);
		User user = this.userService.getUser(userId);
		user.setReview(review);
		review.setUser(user);
		return this.reviewRepository.save(review);
	}

	public void deleteReview(Long reviewId) {
		this.reviewRepository.delete(this.findReviewById(reviewId));
		
	}

}
