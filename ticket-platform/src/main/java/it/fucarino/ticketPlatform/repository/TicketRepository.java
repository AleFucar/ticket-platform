package it.fucarino.ticketPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.fucarino.ticketPlatform.model.Category;
import it.fucarino.ticketPlatform.model.Note;
import it.fucarino.ticketPlatform.model.Status;
import it.fucarino.ticketPlatform.model.Ticket;
import it.fucarino.ticketPlatform.model.User;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Query(value = "select * from ticket join ticket_note on ticket.id = ticket_note.ticket_id ", nativeQuery = true)
	public List<Ticket> findAllNote(List<Note> note);

	@Query(value = "select t.* from ticket t join user u on t.user_id = u.id where u.name = ?1 ", nativeQuery = true)
	public List<Ticket> findByUserName(String username);

	@Query(value = "update ticket set status_id = ?1 where id = ?2 ", nativeQuery = true)
	public List<Ticket> changeStatus(Integer statusId, Integer ticketId);

	@Query(value = "select t.* ,s.name_status from ticket t  join status s on t.status_id = s.id where s.id = 1", nativeQuery = true)
	public List<Ticket> checkTicketStatus(List<Ticket> ticket);

	List<Ticket> findByUser(User user);

	
	@Query(value = "select t.* ,s.name_status from ticket t  join status s on t.status_id = s.id where s.id = ?1", nativeQuery = true)
	public List<Ticket> findByStatusId(Integer idInteger);
	
	@Query(value = "select t.*  from ticket t  join category c on t.category_id = c.id where c.id = ?1", nativeQuery = true)
	public List<Ticket> findByCategoryId(Integer idInteger);
	
}
