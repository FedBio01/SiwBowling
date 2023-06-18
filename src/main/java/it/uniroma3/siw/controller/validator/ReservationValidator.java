package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Reservation;
import it.uniroma3.siw.service.BowlingAlleyService;

@Component
public class ReservationValidator implements Validator {
	
	@Autowired
	private BowlingAlleyService bowlingAlleyService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Reservation reservation = (Reservation)o;
		if (reservation.getReservationDate()!=null && reservation.getReservationTime()!=null
				&& bowlingAlleyService.alleysNotReserved(reservation.getReservationDate(), reservation.getReservationTime())
				.size()==0)
				 {
			errors.reject("reservation.duplicate");
		}
		
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Reservation.class.equals(aClass);
	}
}


