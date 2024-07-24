package it.fucarino.ticketPlatform.controller;

import java.util.List;

import javax.swing.ListCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.fucarino.ticketPlatform.model.Ticket;
import it.fucarino.ticketPlatform.model.User;
import it.fucarino.ticketPlatform.repository.TicketRepository;
import it.fucarino.ticketPlatform.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;
	
	
	
	@GetMapping("/ticket")
	public String index(Model model) {
		
		List<Ticket> ticket = ticketRepository.findAll();
		model.addAttribute("list", ticket);
		return "/dashboard/index";
	}
	
	
	
}
