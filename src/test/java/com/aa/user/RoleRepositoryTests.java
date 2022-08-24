package com.aa.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository repo;
	
	@Test
	public void testCreateRole() {
		Role role = new Role("Admin", "Manage everything");
		
		Role savedRole = repo.save(role);
		
		assertThat(savedRole).isNotNull();
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateMultipleRoles() {
		Role salesPerson = new Role("SalesPerson", "Manage product price, customers, shipping, orders and sales report");
		Role editor = new Role("Editor", "Manage categories, brands, products, articles and menus");
		Role shipper = new Role("Shipper", "View products, view orders and update order status");
		Role assistant = new Role("Assistant", "Manage questions and reviews");

		repo.saveAll(List.of(salesPerson, editor, shipper, assistant));
	}
}
