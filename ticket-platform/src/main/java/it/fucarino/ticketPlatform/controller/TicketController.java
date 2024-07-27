package it.fucarino.ticketPlatform.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.status.StatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import it.fucarino.ticketPlatform.model.Note;
import it.fucarino.ticketPlatform.model.Role;
import it.fucarino.ticketPlatform.model.Status;
import it.fucarino.ticketPlatform.model.Ticket;
import it.fucarino.ticketPlatform.repository.CategoryRepository;
import it.fucarino.ticketPlatform.repository.NoteRepository;
import it.fucarino.ticketPlatform.repository.RoleRepository;
import it.fucarino.ticketPlatform.repository.StatusRepository;
import it.fucarino.ticketPlatform.repository.TicketRepository;
import it.fucarino.ticketPlatform.repository.UserRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	
	@GetMapping("/ticket")
	public String index(Model model) {
		
		List<Ticket> ticket = ticketRepository.findAll();
		model.addAttribute("list", ticket);
		return "/ticket/index";
	}
	
	@GetMapping("/ticket/dettagli/{id}")
	public String dettagli(@PathVariable("id") Integer ticketId, Model model) {
		
		model.addAttribute("ticketDettaglio", ticketRepository.getReferenceById(ticketId));
		return"/ticket/dettagli";
	}
	
	
	@GetMapping("/create")
	public String creation(Model model) {

		model.addAttribute("ticket", new Ticket());
		List<Role> roles = roleRepository.findAll();
		model.addAttribute("statoBase", statusRepository.findAll());
		model.addAttribute("operator", userRepository.findByRoles(roles));
		model.addAttribute("categoryes", categoryRepository.findAll());
		
		return"/ticket/create";
	}
	
	
	
	@PostMapping("/ticket/create")
	public String createPost(@Valid @ModelAttribute("ticket") Ticket ticketForm, Model model) {
		
		ticketRepository.save(ticketForm);
		
		return "redirect:/ticket";
	}
	
	
	
	
	
}
