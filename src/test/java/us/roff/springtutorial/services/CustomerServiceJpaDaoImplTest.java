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
import us.roff.springtutorial.domain.Customer;

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
		assertEquals(expectedCustomer.getAddressLine1(), actualCustomer.getAddressLine1());
		assertEquals(expectedCustomer.getAddressLine2(), actualCustomer.getAddressLine2());
		assertEquals(expectedCustomer.getCity(), actualCustomer.getCity());
		assertEquals(expectedCustomer.getState(), actualCustomer.getState());
		assertEquals(expectedCustomer.getZipCode(), actualCustomer.getZipCode());
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
		customer.setAddressLine1(addressLine1);
		customer.setAddressLine2(addressLine2);
		customer.setCity(city);
		customer.setState(state);
		customer.setZipCode(zipCode);
		
		Customer savedCustomer = customerService.saveOrUpdate(customer);
		assertEquals(firstName, savedCustomer.getFirstName());
		assertEquals(lastName, savedCustomer.getLastName());
		assertEquals(emailAddress, savedCustomer.getEmailAddress());
		assertEquals(phoneNumber, savedCustomer.getPhoneNumber());
		assertEquals(addressLine1, savedCustomer.getAddressLine1());
		assertEquals(addressLine2, savedCustomer.getAddressLine2());
		assertEquals(city, savedCustomer.getCity());
		assertEquals(state, savedCustomer.getState());
		assertEquals(zipCode, savedCustomer.getZipCode());
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
		customer.setAddressLine1(addressLine1);
		customer.setAddressLine2(addressLine2);
		customer.setCity(city);
		customer.setState(state);
		customer.setZipCode(zipCode);
		
		Customer savedCustomer = customerService.saveOrUpdate(customer);
		
		assertEquals(customer.getId(), savedCustomer.getId());
		assertEquals(customer.getVersion().intValue() + 1, savedCustomer.getVersion().intValue());
		assertEquals(customer.getFirstName(), savedCustomer.getFirstName());
		assertEquals(customer.getLastName(), savedCustomer.getLastName());
		assertEquals(customer.getEmailAddress(), savedCustomer.getEmailAddress());
		assertEquals(customer.getPhoneNumber(), savedCustomer.getPhoneNumber());
		assertEquals(customer.getAddressLine1(), savedCustomer.getAddressLine1());
		assertEquals(customer.getAddressLine2(), savedCustomer.getAddressLine2());
		assertEquals(customer.getCity(), savedCustomer.getCity());
		assertEquals(customer.getState(), savedCustomer.getState());
		assertEquals(customer.getZipCode(), savedCustomer.getZipCode());
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
