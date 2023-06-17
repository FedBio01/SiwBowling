package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.BowlingAlley;
import it.uniroma3.siw.repository.BowlingAlleyRepository;

@Component
public class BowlingAlleyValidator implements Validator {
	@Autowired
	private BowlingAlleyRepository bowlingAlleyRepository;

	@Override
	public void validate(Object o, Errors errors) {
		BowlingAlley movie = (BowlingAlley)o;
		if (movie.getTitle()!=null && movie.getYear()!=null 
				&& bowlingAlleyRepository.existsByTitleAndYear(movie.getTitle(), movie.getYear())) {
			errors.reject("movie.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return BowlingAlley.class.equals(aClass);
	}
}