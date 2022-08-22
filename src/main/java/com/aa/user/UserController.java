package com.aa.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = service.listUsers();
		
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("pageTitle", "List Users");
		
		return "users/users";
	}

	@GetMapping("/users/new")
	public String newUser(Model model) {
		List<Role> listRoles = service.listRoles();
		
		model.addAttribute("user", new User());
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("pageTitle", "Create User");
		
		return "users/user_form";
	}
	
	@PostMapping("/users/save")
	public String addUser(User user, RedirectAttributes ra) {
		service.save(user);
		ra.addFlashAttribute("message", "The user has been saved successfully!");
		
		return "redirect:/users";
	}
}
