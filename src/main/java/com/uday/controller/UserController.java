package com.uday.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uday.entity.Notes;
import com.uday.entity.User;
import com.uday.repository.UserRepository;
import com.uday.service.NotesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NotesService notesService;

	@Autowired
	private UserRepository userRepo;

	@ModelAttribute
	public User getUser(Principal p, Model m) {
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return user;
	}


	@GetMapping("/addNotes")
	public String addNotes() {
		return "add_notes";
	}

	@GetMapping("/viewNotes")
	public String viewNotes(Model model,Principal principal) {
		User user = getUser(principal, model);
		List<Notes> notes = notesService.getNotesByUser(user);
		model.addAttribute("notesList",notes);
		return "view_notes";
	}

	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id, Model model) {
		Notes notes = notesService.getNotesById(id);
		model.addAttribute("n",notes);
		return "edit_notes";
	}
	
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes, HttpSession session,Principal principal,Model model)
	{
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(principal, model));
		
		Notes saveNotes = notesService.saveNotes(notes);
		if (saveNotes != null) {
			session.setAttribute("msg", "Notes Save Success");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}
		return "redirect:/user/addNotes";
		
	}

	
	@PostMapping("/editNotes")
	public String updateNotes(@ModelAttribute Notes notes, HttpSession session,Principal principal,Model model)
	{
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(principal, model));
		
		Notes saveNotes = notesService.saveNotes(notes);
		if (saveNotes != null) {
			session.setAttribute("msg", "Notes Update Success");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}
		return "redirect:/user/viewNotes";
		
	}
	
	
	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable int id,HttpSession session ) {
		boolean notes = notesService.deleteNotes(id);
		if (notes) {
			session.setAttribute("msg", "Notes Delete Success");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}
		return "redirect:/user/viewNotes";
	}
	
	
}
