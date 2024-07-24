package it.fucarino.ticketPlatform.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;

	
	@OneToOne
	@JoinColumn(name = "status_id")
	private Status status;

	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Note> note;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}


	public String getUserName() {
		return user.getName();
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Category getCategory(){
		return category;
	}
	
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


	public List<Note> getNote() {
		return note;
	}


	public void setNote(List<Note> note) {
		this.note = note;
	}
	
	
	
	
	
}
