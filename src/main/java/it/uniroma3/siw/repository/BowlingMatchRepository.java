package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.BowlingMatch;

public interface BowlingMatchRepository extends CrudRepository<BowlingMatch, Long> {

	public List<BowlingMatch> findByYear(int year);

	public boolean existsByTitleAndYear(String title, int year);	
}