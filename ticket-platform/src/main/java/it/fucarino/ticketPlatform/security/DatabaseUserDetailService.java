
package it.fucarino.ticketPlatform.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.fucarino.ticketPlatform.model.User;
import it.fucarino.ticketPlatform.repository.UserRepository;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByName(username);
		
		if (user.isPresent()) {
			return new DatabaseUserDetail(user.get());
		}else {
			throw new UsernameNotFoundException("Username non trovato");
		}
	}
	
	
	public UserDetails loadByUserId(Integer id) {
		
		Optional<User> userid = userRepository.findById(id);
		
		if (userid.isPresent()) {
			return new DatabaseUserDetail(userid.get());
		}else {
			throw new UsernameNotFoundException("id user non trovato");
		}
		
		
		
	}
	
}
