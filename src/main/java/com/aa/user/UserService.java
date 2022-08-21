package com.aa.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> listUsers() {
		return (List<User>) repo.findAll();
	}
	
	
}
