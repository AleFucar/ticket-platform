package it.fucarino.ticketPlatform.controller;

import java.util.List;
import java.util.Optional;

import javax.swing.event.TableColumnModelListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.fucarino.ticketPlatform.model.Role;
import it.fucarino.ticketPlatform.model.Ticket;
import it.fucarino.ticketPlatform.model.User;
import it.fucarino.ticketPlatform.repository.PersonalRepository;
import it.fucarino.ticketPlatform.repository.RoleRepository;
import it.fucarino.ticketPlatform.repository.TicketRepository;
import it.fucarino.ticketPlatform.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/operator")
public class OperatorController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private PersonalRepository personalRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	

	@GetMapping("/show")
	public String showOperator( Authentication authentication , Model model) {
			
			Optional<User> user = userRepository.findByName(authentication.getName());
			model.addAttribute("user", user.get());
			
			List<Ticket> ticket = ticketRepository.findByUserName(authentication.getName());
			model.addAttribute("ticketDettaglio", ticket);
			
			boolean allComplete = true;
			
			for (Ticket ticket2 : ticket) {
				if (!"Completato".equals(ticket2.getStatusName().toString())) {
					allComplete = false;
					break;
				}
			}
				model.addAttribute("control", allComplete);
				
			return "/operator/index";
		
	}
	
	
	@GetMapping("/info/{id}")
	public String showSingleOperator(@PathVariable("id") Integer operatorId, Model  model) {
		
		List<Ticket> ticket = ticketRepository.findByUser(userRepository.getReferenceById(operatorId));
		model.addAttribute("ticketDettaglio", ticket);
		model.addAttribute("operatorById", userRepository.getReferenceById(operatorId));
		
		return "/operator/info";
	}
	
	
	
	@GetMapping("/status")
	public String showStatus( Authentication authentication , Model model) {
		
		if (authentication.getName().equals(userRepository.findAdmin())) {
			
			List<User> user = userRepository.findAll();
			model.addAttribute("user", user);
			
			return "/operator/index";
		}
			
			Optional<User> user = userRepository.findByName(authentication.getName());
			model.addAttribute("user", user.get());
			model.addAttribute("personal", personalRepository.findAll());
			List<Role> roles = roleRepository.findAll();
			model.addAttribute("roles", roles);
	

			return "/operator/status";
		
	}
	
	
	
	@PostMapping("/status")
	public String post(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "/operator/status";
		}
		
		userRepository.save(user);
		
		return "redirect:/operator/show";
	}
	
	
		
}
