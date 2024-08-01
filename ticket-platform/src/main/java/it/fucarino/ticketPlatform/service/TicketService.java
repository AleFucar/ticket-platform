package it.fucarino.ticketPlatform.service;

import java.util.List;
import java.util.Optional;

import it.fucarino.ticketPlatform.model.Status;
import it.fucarino.ticketPlatform.model.Ticket;

public interface TicketService {

	public Optional<Ticket> findById(Integer id);
	
	public List<Ticket> findAll();
	
	public List<Ticket> findByStatusId(Integer idInteger);
	
	public List<Ticket> findByCategoryId(Integer idInteger);

}
