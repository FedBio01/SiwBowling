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
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}


}