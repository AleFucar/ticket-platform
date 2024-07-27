package it.fucarino.ticketPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fucarino.ticketPlatform.model.Personal;

public interface PersonalRepository extends JpaRepository<Personal, Integer> {

}
