package it.fucarino.ticketPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fucarino.ticketPlatform.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}
