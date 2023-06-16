package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.BowlingAlley;

public interface BowlingAlleyRepository extends CrudRepository<BowlingAlley, Long> {

	public List<BowlingAlley> findByYear(int year);

	public boolean existsByTitleAndYear(String title, int year);	
}