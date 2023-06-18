package it.uniroma3.siw.service;

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

}
