package com.aa.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Integer id, Model model,
			RedirectAttributes ra) {
		try {
			List<Role> listRoles = service.listRoles();
			User user = service.get(id);
			
			model.addAttribute("user", user);
			model.addAttribute("listRoles", listRoles);
			model.addAttribute("pageTitle", "Edit User (ID:" + id + ")");
			
			return "users/user_form";
		} catch (UserNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
			return "redirect:/users";
		}
	}
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
		try {
			service.delete(id);
			
			ra.addFlashAttribute("message", "The user with ID: " + id + " was deleted successfully!");
		} catch (UserNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
		}
		
		return "redirect:/users";
	}
}
