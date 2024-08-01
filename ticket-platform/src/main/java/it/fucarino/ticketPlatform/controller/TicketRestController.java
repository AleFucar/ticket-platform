package it.fucarino.ticketPlatform.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.fucarino.ticketPlatform.model.Status;
import it.fucarino.ticketPlatform.model.Ticket;
import it.fucarino.ticketPlatform.response.Payload;
import it.fucarino.ticketPlatform.service.TicketService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@CrossOrigin
@RequestMapping("/api")
public class TicketRestController {
	
	@Autowired
	private TicketService ticketService;
	

	@GetMapping("/ticket/{id}")
	public ResponseEntity<Payload<Ticket>> get(@PathVariable("id") Integer idTicket){
	
		Optional<Ticket> ticket = ticketService.findById(idTicket);
		
		if (ticket.isPresent()) {
			return ResponseEntity.ok(new Payload<Ticket>(ticket.get(), null, HttpStatus.OK));
		}else {
			return ResponseEntity.ok(new Payload<Ticket>(null, "Ticket con id " + idTicket + " non trovato", HttpStatus.NOT_FOUND));
		}
		
	}
	
	@GetMapping("/ticket")
	public ResponseEntity findAllTickets() {
		
		List<Ticket> tickets = ticketService.findAll();
		
		
		if (!tickets.isEmpty()) {
			
			return new ResponseEntity<>(tickets, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Ticket non trovati ", HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@GetMapping("/ticket/status/{id}")
	public ResponseEntity  findByStatus(@PathVariable("id") Integer status){
		
		List<Ticket> ticket = ticketService.findByStatusId(status);
		
		if (!ticket.isEmpty()) {
			return new ResponseEntity<>(ticket, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Non ci sono Ticket con lo stato: " + status, HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	@GetMapping("/ticket/category/{id}")
	public ResponseEntity findByCategory(@PathVariable("id") Integer category){
		
		List<Ticket> ticket = ticketService.findByCategoryId(category);
		
		if (!ticket.isEmpty()) {
			return new ResponseEntity<>(ticket, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Non ci sono Ticket con la categoria: " + category, HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
}
