package it.fucarino.ticketPlatform.controller;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import it.fucarino.ticketPlatform.model.Note;
import it.fucarino.ticketPlatform.model.Role;
import it.fucarino.ticketPlatform.model.Status;
import it.fucarino.ticketPlatform.model.Ticket;
import it.fucarino.ticketPlatform.model.User;
import it.fucarino.ticketPlatform.repository.CategoryRepository;
import it.fucarino.ticketPlatform.repository.NoteRepository;
import it.fucarino.ticketPlatform.repository.RoleRepository;
import it.fucarino.ticketPlatform.repository.StatusRepository;
import it.fucarino.ticketPlatform.repository.TicketRepository;
import it.fucarino.ticketPlatform.repository.UserRepository;
import it.fucarino.ticketPlatform.security.DatabaseUserDetail;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;






@Controller
@RequestMapping
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	
	@Autowired
	private StatusRepository statusRepository;
	
	
	@Autowired
	private NoteRepository noteRepository;
	
	
	
	@GetMapping("/ticket")
	public String index(@RequestParam(value = "cerca", required = false) String cercaTitolo  ,Authentication authentication,Model model) {
		
		
		List<Role> roles = roleRepository.findAll();
		model.addAttribute("operatorByRole", userRepository.findByRoless(roles));
		model.addAttribute("operator", userRepository.findByRolesAndFree(roles));
		
		List<Ticket> ticketFound;
		
		if (authentication.getName().equals(userRepository.findAdmin())) {
			
			ticketFound = ticketRepository.findAll();
			
		}else {
			
			ticketFound = ticketRepository.findByUserName(authentication.getName());
		}
			
		
		if (cercaTitolo != null && !cercaTitolo.isEmpty()) {
			
			List<Ticket> filteredTickets = new ArrayList<>();
			
			for (Ticket ticket : ticketFound) {
				if (ticket.getTitle() != null && ticket.getTitle().toLowerCase().contains(cercaTitolo.toLowerCase())) {
					filteredTickets.add(ticket);
				}
			}
			ticketFound = filteredTickets;
		}
		
		
		model.addAttribute("list", ticketFound);
		model.addAttribute("cercaTitolo", cercaTitolo);
		
		return "/ticket/index";
	}
	
	
	
             //	DETTAGLI TICKET//
	
	
	@GetMapping("/ticket/dettagli/{id}")
	public String dettagli(@PathVariable("id") Integer ticketId, Authentication authentication , Model model) {
		
		List<Role> roles = roleRepository.findAll();
		model.addAttribute("operatorByRole", userRepository.findByRoless(roles));
		
		
		Ticket ticket = ticketRepository.findById(ticketId).get();
		Note note = new Note();
		note.setTicket(ticket);
		note.setAuthor(authentication.getName());
		note.setDateTime(LocalDateTime.now());
		model.addAttribute("note", note);
		
	
		model.addAttribute("ticketDettaglio", ticketRepository.getReferenceById(ticketId));
		
		return"/ticket/dettagli";
	}
	
	
	
            //	MODIFICA TICKET//	
	
	
	@GetMapping("/ticket/modifica/{id}")
	public String update(@PathVariable("id") Integer ticketId, Model model) {
		
		

		

		List<Role> roles = roleRepository.findAll();
		model.addAttribute("operatorByRole", userRepository.findByRoless(roles));
		model.addAttribute("statoBase", statusRepository.findAll());
		model.addAttribute("operator", userRepository.findByRolesAndFree(roles));
		model.addAttribute("categoryes", categoryRepository.findAll());
		model.addAttribute("ticket", ticketRepository.getReferenceById(ticketId));
		model.addAttribute("note", noteRepository.getReferenceById(ticketId));
		model.addAttribute("notaNew", new Note());
		
		return "/ticket/modifica";
	}
	
	
	@PostMapping("/ticket/modifica/{id}")
	public String postUpdate(@Valid @ModelAttribute("ticket") Ticket ticketForm, BindingResult bindingResult, @PathVariable("id") Integer ticketId,  Model model) {

		if (bindingResult.hasErrors()) {
			
			List<Role> roles = roleRepository.findAll();
			model.addAttribute("statoBase", statusRepository.findAll());
			model.addAttribute("operator", userRepository.findByRolesAndFree(roles));
			model.addAttribute("categoryes", categoryRepository.findAll());
			model.addAttribute("note", noteRepository.getReferenceById(ticketId));
			model.addAttribute("notaNew", new Note());
			
			return "/ticket/modifica";
		}
		
		ticketRepository.save(ticketForm);
		
		return "redirect:/ticket";
	}
	
	
	
	
	@GetMapping("/ticket/stato/{id}")
	public String updateStatusTicket(@PathVariable("id") Integer ticketId, Model model) {
	

		List<Role> roles = roleRepository.findAll();
		model.addAttribute("operatorByRole", userRepository.findByRoless(roles));
		model.addAttribute("statoBase", statusRepository.findAll());
		model.addAttribute("operator", userRepository.findByRolesAndFree(roles));
		model.addAttribute("categoryes", categoryRepository.findAll());
		model.addAttribute("ticket", ticketRepository.getReferenceById(ticketId));
		model.addAttribute("note", noteRepository.getReferenceById(ticketId));
		
		
		return "/ticket/stato";
	}
	
	
	@PostMapping("/ticket/stato/{id}")
	public String postUpdateStatusTicket(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult ,Model model) {

		if (bindingResult.hasErrors()) {
			
			return "/ticket/stato";
		}
		
		ticketRepository.save(ticket);
		
		return "redirect:/ticket";
	}

	
	
	
			//	ELIMINA TICKET//
	
	
	@PostMapping("/delete/{id}")
	public String postDelete(@PathVariable("id") Integer id) {
	
		ticketRepository.deleteById(id);
		
		return "redirect:/ticket";
	}
	
	
	
	
	
	
            //	CREAZIONE TICKET//
	
	@GetMapping("/create")
	public String creation(Model model) {

		model.addAttribute("ticket", new Ticket());
		List<Role> roles = roleRepository.findAll();
		model.addAttribute("operatorByRole", userRepository.findByRoless(roles));
		model.addAttribute("statoBase", statusRepository.findAll());
		model.addAttribute("operator", userRepository.findByRolesAndFree(roles));
		model.addAttribute("categoryes", categoryRepository.findAll());
		
		
		return"/ticket/create";
	}
	
	
	
	@PostMapping("/create")
	public String createPost(@Valid @ModelAttribute("ticket") Ticket ticketForm ,BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			List<Role> roles = roleRepository.findAll();
			model.addAttribute("statoBase", statusRepository.findAll());
			model.addAttribute("operator", userRepository.findByRolesAndFree(roles));
			model.addAttribute("categoryes", categoryRepository.findAll());
			
			return "/ticket/create";
		}
		
		ticketRepository.save(ticketForm);
		
		return "redirect:/ticket";
	}	
	
	
}
