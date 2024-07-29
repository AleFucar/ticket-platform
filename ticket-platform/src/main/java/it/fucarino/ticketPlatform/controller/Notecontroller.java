package it.fucarino.ticketPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import it.fucarino.ticketPlatform.model.Note;
import it.fucarino.ticketPlatform.repository.NoteRepository;
import it.fucarino.ticketPlatform.repository.TicketRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/note")
public class Notecontroller {
	
	@Autowired
	private NoteRepository noteRepository;
	

	
	@PostMapping("/create")
	public String postCreate(@Valid @ModelAttribute("note") Note note , BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return"/ticket/dettagli/" + note.getTicket().getId();
		}

		noteRepository.save(note);
		
		return "redirect:/ticket/dettagli/" + note.getTicket().getId();
	}
	
	
}
