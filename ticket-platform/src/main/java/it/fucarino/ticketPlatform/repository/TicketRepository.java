package it.fucarino.ticketPlatform.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.fucarino.ticketPlatform.model.Note;
import it.fucarino.ticketPlatform.model.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Query(value = "select * from ticket join ticket_note on ticket.id = ticket_note.ticket_id ", nativeQuery = true)
	public List<Ticket> findAllNote(List<Note> note);
	

	@Query(value = "select t.* from ticket t join user u on t.user_id = u.id where u.name = ?1 ", nativeQuery = true)
	public List<Ticket> findByUserName(String username);
	
}
