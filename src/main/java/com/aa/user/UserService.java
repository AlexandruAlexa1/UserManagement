package com.aa.user;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

	@Autowired private UserRepository userRepo;
	@Autowired private RoleRepository roleRepo;
	
	public List<User> listUsers() {
		return (List<User>) userRepo.findAll();
	}
	
	public List<Role> listRoles() {
		return (List<Role>) roleRepo.findAll();
	}
	
	public void save(User user) {
		userRepo.save(user);
	}

	public User get(Integer id) throws UserNotFoundException {
		try {
			return userRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new UserNotFoundException("Could not find any user with ID: " + id);
		}
	}
	
	public void updateEnabledStatus(Integer id, boolean enabled) {
		userRepo.updateEnabledStatus(id, enabled);
	}
	
	public void delete(Integer id) throws UserNotFoundException {
		if (!userRepo.existsById(id)) {
			throw new UserNotFoundException("Could not find any user with ID: " + id);
		}
		
		userRepo.deleteById(id);
	}
}
