package it.fucarino.ticketPlatform.model;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "e-mail")
	@NotBlank(message = "La mail non può essere vuota")
	private String eMail;
	
	@Column(name = "password")
	@NotBlank(message = "La password non può essere vuota")
	private String password;
	
	@Column(name = "name")
	@NotBlank(message = "Il nome non può essere vuoto")
	private String name;
	
	@Column(name = "surname")
	@NotBlank(message = "Il cognome non può essere vuoto")
	private String surname;
	
	@Column(name = "photo")
	private String photo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;
	
    @OneToMany(mappedBy = "user")
    private List<Ticket> ticket;
    
	@ManyToOne
	@JoinColumn(name = "personal_id", nullable = false)
	private Personal personal;
    
    
    
	// Getter and Setter //
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}



	
}
