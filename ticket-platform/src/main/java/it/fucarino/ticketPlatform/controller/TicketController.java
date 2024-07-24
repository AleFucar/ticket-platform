package it.fucarino.ticketPlatform.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.fucarino.ticketPlatform.model.Ticket;
import it.fucarino.ticketPlatform.repository.TicketRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequestMapping
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;
	
	
	
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
	
}
