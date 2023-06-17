package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Reservation;
import static it.uniroma3.siw.model.Reservation.TOTAL_BOWLING_ALLEY;
import it.uniroma3.siw.service.ReservationService;

@Component
public class ReservationValidator implements Validator {
	
	@Autowired
	private ReservationService reservationService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Reservation reservation = (Reservation)o;
		if (reservation.getReservationDate()!=null && reservation.getReservationTime()!=null
				&& reservationService.findReservationsByDateAndTime(reservation.getReservationDate(), reservation.getReservationTime())
				.size()>=TOTAL_BOWLING_ALLEY)
				 {
			errors.reject("reservation.duplicate");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Reservation.class.equals(aClass);
	}
}


