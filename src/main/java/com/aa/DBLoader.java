package com.aa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aa.user.Role;
import com.aa.user.RoleRepository;
import com.aa.user.User;
import com.aa.user.UserRepository;

@Configuration
public class DBLoader {
	
	@Autowired private UserRepository userRepo;
	@Autowired private RoleRepository roleRepo;
	
	@Bean
	public CommandLineRunner initDB() {
		return args -> {
			addRoles();
			addUsers();
		};
	}

	private void addUsers() {
		User user1 = new User("Alex", "Stevenson", "alex1.stevenson@outlook.com", "password");
		user1.setRole(new Role(1));
		user1.setRole(new Role(2));
		
		User user2 = new User("Alisa", "Willcox", "alisa.willcox7@gmail.com", "password");
		user2.setRole(new Role(2));
		
		User user3 = new User("Avatar", "Cheema", "cheema1994@gmail.com", "password");
		user3.setRole(new Role(3));
		user3.setRole(new Role(4));
		
		User user4 = new User("Anh", "Le Hoang", "lehoanganhvn@gmail.com", "password");
		user4.setRole(new Role(4));
		
		User user5 = new User("Ethan", "Jones", "ethan.k.john@gmail.com", "password");
		user5.setRole(new Role(5));
		
		User user6 = new User("Flynn", "Hackett", "flynn.hackett.au@gmail.com", "password");
		user6.setRole(new Role(4));
		
		User user7 = new User("Gautam", "Nayak", "gautam1988@gmail.com", "password");
		user7.setRole(new Role(3));
		
		User user8 = new User("Jahnu", "Stevenson", "jahnu.mishra@gmail.com", "password");
		user8.setRole(new Role(2));
		user8.setRole(new Role(3));
		
		User user9 = new User("Mo", "Liang", "mo.liang.237@gmail.com", "password");
		user9.setRole(new Role(1));
		
		User user10 = new User("Ravi", "Kumar", "ravi.kumar2009@gmail.com", "password");
		user10.setRole(new Role(2));
				
		userRepo.saveAll(List.of(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));
	}

	private void addRoles() {
		Role admin = new Role("Admin", "Manage everything");
		Role salesPerson = new Role("SalesPerson", "Manage product price, customers, shipping, orders and sales report");
		Role editor = new Role("Editor", "Manage categories, brands, products, articles and menus");
		Role shipper = new Role("Shipper", "View products, view orders and update order status");
		Role assistant = new Role("Assistant", "Manage questions and reviews");

		roleRepo.saveAll(List.of(admin, salesPerson, editor, shipper, assistant));
	}
}
