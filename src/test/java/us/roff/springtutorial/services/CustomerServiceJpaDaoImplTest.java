package us.roff.springtutorial.services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import us.roff.springtutorial.config.JpaIntegrationConfig;
import us.roff.springtutorial.domain.Address;
import us.roff.springtutorial.domain.Customer;
import us.roff.springtutorial.domain.User;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class CustomerServiceJpaDaoImplTest {
	
	private CustomerService customerService;
	
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Test
	public void testListAll() throws Exception {
		List<Customer> customers = (List<Customer>)customerService.listAll();
		assertEquals(2, customers.size());
	}
	
	@Test
	public void testGetById() throws Exception {
		List<Customer> customers = (List<Customer>)customerService.listAll();
		assertTrue(customers.size() > 0);
		
		Customer expectedCustomer = customers.get(0);
		
		Customer actualCustomer = customerService.getById(expectedCustomer.getId());
		assertNotNull(actualCustomer);
		
		// Check fields
		assertEquals(expectedCustomer.getId(), actualCustomer.getId());
		assertEquals(expectedCustomer.getVersion(), actualCustomer.getVersion());
		assertEquals(expectedCustomer.getFirstName(), actualCustomer.getFirstName());
		assertEquals(expectedCustomer.getLastName(), actualCustomer.getLastName());
		assertEquals(expectedCustomer.getEmailAddress(), actualCustomer.getEmailAddress());
		assertEquals(expectedCustomer.getPhoneNumber(), actualCustomer.getPhoneNumber());
		assertEquals(expectedCustomer.getBillingAddress().getAddressLine1(), actualCustomer.getBillingAddress().getAddressLine1());
		assertEquals(expectedCustomer.getBillingAddress().getAddressLine2(), actualCustomer.getBillingAddress().getAddressLine2());
		assertEquals(expectedCustomer.getBillingAddress().getCity(), actualCustomer.getBillingAddress().getCity());
		assertEquals(expectedCustomer.getBillingAddress().getState(), actualCustomer.getBillingAddress().getState());
		assertEquals(expectedCustomer.getBillingAddress().getZipCode(), actualCustomer.getBillingAddress().getZipCode());
	}
	
	@Test
	@DirtiesContext
	public void testNew() throws Exception {
		String firstName = "Nanook";
		String lastName = "OfTheNorth";
		String emailAddress = "nanook@fargo";
		String phoneNumber = "222-555-1212";
		String addressLine1 = "99 Igloo St.";
		String addressLine2 = "Apt 5A";
		String city = "Fargo";
		String state = "ND";
		String zipCode = "99999";
		
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmailAddress(emailAddress);
		customer.setPhoneNumber(phoneNumber);
		Address address = new Address();
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setCity(city);
		address.setState(state);
		address.setZipCode(zipCode);
		customer.setBillingAddress(address);
		
		Customer savedCustomer = customerService.saveOrUpdate(customer);
		assertEquals(firstName, savedCustomer.getFirstName());
		assertEquals(lastName, savedCustomer.getLastName());
		assertEquals(emailAddress, savedCustomer.getEmailAddress());
		assertEquals(phoneNumber, savedCustomer.getPhoneNumber());
		assertEquals(addressLine1, savedCustomer.getBillingAddress().getAddressLine1());
		assertEquals(addressLine2, savedCustomer.getBillingAddress().getAddressLine2());
		assertEquals(city, savedCustomer.getBillingAddress().getCity());
		assertEquals(state, savedCustomer.getBillingAddress().getState());
		assertEquals(zipCode, savedCustomer.getBillingAddress().getZipCode());
	}
	
	@Test
	@DirtiesContext
	public void testNewWithUser() throws Exception {
		String username = "someuser";
		String password = "password";
		
		Customer customer = new Customer();
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		customer.setUser(user);
		
		Customer savedCustomer = customerService.saveOrUpdate(customer);
		assertNotNull(savedCustomer);
		assertNotNull(savedCustomer.getId());
		assertNotNull(savedCustomer.getUser());
		assertNotNull(savedCustomer.getUser().getId());
		assertNotNull(savedCustomer.getUser().getEncryptedPassword());
	}
	
	@Test
	public void testUpdate() throws Exception {
		List<Customer> customers = (List<Customer>)customerService.listAll();
		assertTrue(customers.size() > 0);
		
		Customer customer = customers.get(0);
		
		String firstName = "Nanook";
		String lastName = "OfTheNorth";
		String emailAddress = "nanook@fargo";
		String phoneNumber = "222-555-1212";
		String addressLine1 = "99 Igloo St.";
		String addressLine2 = "Apt 5A";
		String city = "Fargo";
		String state = "ND";
		String zipCode = "99999";
		
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmailAddress(emailAddress);
		customer.setPhoneNumber(phoneNumber);
		Address address = new Address();
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setCity(city);
		address.setState(state);
		address.setZipCode(zipCode);
		customer.setBillingAddress(address);
		
		Customer savedCustomer = customerService.saveOrUpdate(customer);
		
		assertEquals(customer.getId(), savedCustomer.getId());
		assertEquals(customer.getVersion().intValue() + 1, savedCustomer.getVersion().intValue());
		assertEquals(customer.getFirstName(), savedCustomer.getFirstName());
		assertEquals(customer.getLastName(), savedCustomer.getLastName());
		assertEquals(customer.getEmailAddress(), savedCustomer.getEmailAddress());
		assertEquals(customer.getPhoneNumber(), savedCustomer.getPhoneNumber());
		assertEquals(customer.getBillingAddress().getAddressLine1(), savedCustomer.getBillingAddress().getAddressLine1());
		assertEquals(customer.getBillingAddress().getAddressLine2(), savedCustomer.getBillingAddress().getAddressLine2());
		assertEquals(customer.getBillingAddress().getCity(), savedCustomer.getBillingAddress().getCity());
		assertEquals(customer.getBillingAddress().getState(), savedCustomer.getBillingAddress().getState());
		assertEquals(customer.getBillingAddress().getZipCode(), savedCustomer.getBillingAddress().getZipCode());
	}
	
	@Test
	public void testDeleteById() throws Exception {
		List<Customer> startCustomers = (List<Customer>)customerService.listAll();
		assertTrue(startCustomers.size() > 0);
		
		Integer idToDelete = startCustomers.get(0).getId();
		customerService.deleteById(idToDelete);
		
		List<Customer> endCustomers = (List<Customer>)customerService.listAll();
		assertEquals(startCustomers.size() - 1, endCustomers.size());
		
		for (Customer customer : endCustomers) {
			// Verify deleted id does not exist
			assertNotEquals(idToDelete, customer.getId());
		}
	}
}
