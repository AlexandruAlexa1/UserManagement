package com.aa.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;
	
	@Test
	public void testCreateUser() {
		User user = new User("Alex", "Stevenson", "alex.stevenson@yahoo.com", "0d3dj2js92jd31js2jd3jwd");
		
		User savedUser = repo.save(user);
		
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateUserWithTwoRoles() {
		User user = new User("Alisia", "Willcox", "alissia.willcox7@yahoo.com", "0d3dj2js92jd31js2jd3jwd");
		user.setRole(new Role(1));
		user.setRole(new Role(2));
		
		User savedUser = repo.save(user);
		
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		
		assertThat(listUsers).size().isGreaterThan(0);
		
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		Integer userId = 1;
		
		User user = repo.findById(userId).get();
		
		assertThat(user).isNotNull();
		
		System.out.println(user);
	}
	
	@Test
	public void testUpdateUser() {
		Integer userId = 1;
		
		User user = repo.findById(userId).get();
		user.setEmail("E-mail");
		
		User updatedUser = repo.save(user);
		
		assertThat(updatedUser.getEmail()).isEqualTo("E-mail");
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 1;

		repo.deleteById(userId);
		
		Optional<User> user = repo.findById(userId);
		
		assertThat(!user.isPresent());
	}
	
	@Test
	public void testUpdateEnabledStatus() {
		Integer userId = 2;
		boolean enabled = true;
		
		repo.updateEnabledStatus(userId, enabled);
		
		User updatedUser = repo.findById(userId).get();
		
		assertThat(updatedUser.isEnabled()).isEqualTo(enabled);
	}
}
