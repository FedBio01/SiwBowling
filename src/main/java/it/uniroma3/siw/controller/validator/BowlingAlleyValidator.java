package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.BowlingAlley;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Reservation;
import it.uniroma3.siw.repository.BowlingAlleyRepository;
import it.uniroma3.siw.service.BowlingAlleyService;
import it.uniroma3.siw.service.CredentialsService;

@Component
public class BowlingAlleyValidator implements Validator {
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private BowlingAlleyService bowlingAlleyService;

	@Override
	public void validate(Object o, Errors errors){
		BowlingAlley alley = (BowlingAlley)o;
		if (alley.getAlleyNumber()!=null && this.bowlingAlleyService.existsByAlleyNumber(alley.getAlleyNumber())) {
			errors.reject("bowlingAlley.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return BowlingAlley.class.equals(clazz);
	}

}