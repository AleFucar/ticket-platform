package it.fucarino.ticketPlatform.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.fucarino.ticketPlatform.model.Role;
import it.fucarino.ticketPlatform.model.User;

public class DatabaseUserDetail implements UserDetails {
	

	private final Integer id;
	private final String name;
	private final String password;
	private Set<GrantedAuthority> authorities;
	
	
	public DatabaseUserDetail(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.password = user.getPassword();
		this.authorities = new HashSet<>();
		 for(Role ruolo : user.getRoles()) {
			 this.authorities.add(new SimpleGrantedAuthority(ruolo.getRole()));
		 }
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public Integer getId() {
		return id;
	}
	
	
	
	
}
