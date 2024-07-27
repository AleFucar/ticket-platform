package it.fucarino.ticketPlatform.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import it.fucarino.ticketPlatform.model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {



}
