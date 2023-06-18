package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.BowlingAlley;
import it.uniroma3.siw.repository.BowlingAlleyRepository;

@Service
public class BowlingAlleyService {
	
	@Autowired
	private BowlingAlleyRepository bowlingAlleyRepository;
	
	public BowlingAlley createNewAlley(BowlingAlley alley) {
		BowlingAlley createdAlley= this.bowlingAlleyRepository.save(alley);
		return createdAlley;
	}

	public boolean existsByAlleyNumber(Integer alleyNumber) {
		return this.bowlingAlleyRepository.existsByAlleyNumber(alleyNumber);
	}
	
	public List<BowlingAlley> alleysNotReserved(LocalDate reservationDate,String reservationTime){
		return (List<BowlingAlley>) this.bowlingAlleyRepository.findAlleysNotReserved(reservationDate, reservationTime);
	}

	public List<BowlingAlley> findAllBowlingAlleys() {
		return (List<BowlingAlley>) this.bowlingAlleyRepository.findAll();
	}
	

}
