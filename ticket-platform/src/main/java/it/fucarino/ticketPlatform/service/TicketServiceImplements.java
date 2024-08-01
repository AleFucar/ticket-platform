package it.fucarino.ticketPlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fucarino.ticketPlatform.model.Status;
import it.fucarino.ticketPlatform.model.Ticket;
import it.fucarino.ticketPlatform.repository.TicketRepository;

@Service
public class TicketServiceImplements implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	
	@Override
	public Optional<Ticket> findById(Integer id) {
		
		return ticketRepository.findById(id);
	}
	

	@Override
	public List<Ticket> findAll() {
		
		return ticketRepository.findAll();
	}


	@Override
	public List<Ticket> findByStatusId(Integer idInteger) {
		
		return ticketRepository.findByStatusId(idInteger);
	}


	@Override
	public List<Ticket> findByCategoryId(Integer idInteger) {
		return ticketRepository.findByCategoryId(idInteger);
	}




}
