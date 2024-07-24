package it.fucarino.ticketPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fucarino.ticketPlatform.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
