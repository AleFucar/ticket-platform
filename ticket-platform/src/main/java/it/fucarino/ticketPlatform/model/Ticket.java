package it.fucarino.ticketPlatform.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "title")
	@NotBlank(message = "Il titolo non deve essere vuoto")
	private String title;
	
	@Column(name = "nota_iniziale")
	private String notaIniziale;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonManagedReference
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	@JsonManagedReference
	private Category category;

	
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	@JsonManagedReference
	private Status status;

	
	@OneToMany(mappedBy = "ticket")
	@JsonManagedReference
	private List<Note> note;
	
	
	
	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
	public String getNotaIniziale() {
		return notaIniziale;
	}


	public void setNotaIniziale(String notaIniziale) {
		this.notaIniziale = notaIniziale;
	}


	public List<Note> getNote() {
		return note;
	}


	public void setNote(List<Note> note) {
		this.note = note;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}


	@JsonIgnore
	public String getUserName() {
		return user.getName();
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Category getCategory(){
		return category;
	}
	
	@JsonIgnore
	public String getCategoryName() {
		return category.getCategoryName();
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Status getStatus() {
		return status;
	}
	
	
	public String getStatusName() {
		return status.getNameStatus();
	}


	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
