package it.fucarino.ticketPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fucarino.ticketPlatform.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
