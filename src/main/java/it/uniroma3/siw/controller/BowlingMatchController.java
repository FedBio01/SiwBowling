package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.validator.BowlingMatchValidator;
import it.uniroma3.siw.model.Reservation;
import it.uniroma3.siw.model.BowlingMatch;
import it.uniroma3.siw.repository.ReservationRepository;
import it.uniroma3.siw.repository.BowlingMatchRepository;
import jakarta.validation.Valid;

@Controller
public class BowlingMatchController {

}
