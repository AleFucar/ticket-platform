package it.fucarino.ticketPlatform.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import it.fucarino.ticketPlatform.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	

}
