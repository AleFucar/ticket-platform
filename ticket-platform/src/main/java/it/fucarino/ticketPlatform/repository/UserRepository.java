package it.fucarino.ticketPlatform.repository;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import it.fucarino.ticketPlatform.model.User;
import java.util.List;
import java.util.Optional;

import it.fucarino.ticketPlatform.model.Role;





@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "select * from `user` join user_roles on user.id = user_roles.user_id where user_roles.roles_id = 1", nativeQuery = true)
	public List<User> findByRoles(List<Role> roles);
	
	
	@Query(value = "select user.name from `user` join user_roles on user.id = user_roles.user_id where user_roles.roles_id = 2", nativeQuery = true)
	public String findAdmin();
	
	
	Optional<User> findByName(String name);

}