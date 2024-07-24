package it.fucarino.ticketPlatform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "status_ticket")
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name_status")
	private String nameStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameStatus() {
		return nameStatus;
	}

	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
	}
	
	
	
}
