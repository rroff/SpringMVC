package us.roff.springtutorial.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import scala.annotation.meta.param;
import us.roff.springtutorial.domain.Customer;
import us.roff.springtutorial.domain.Product;
import us.roff.springtutorial.services.CustomerService;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

	@Mock // Mockito Mock Object
	private CustomerService customerService;
	
	@InjectMocks // sets up controller and injects mock objects into it
	private CustomerController customerController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testList() throws Exception {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer());
		customers.add(new Customer());
		
		// specific Mockito interaction to tell stub to return list of products
		when(customerService.listAll()).thenReturn((List)customers);
		
		mockMvc.perform(get("/customer/list"))
			.andExpect(status().isOk())
			.andExpect(view().name("customer/list"))
			.andExpect(model().attribute("customers", hasSize(2)));
	}
	
	@Test
	public void testShow() throws Exception {
		Integer id = 1;
		
		when(customerService.getById(id)).thenReturn(new Customer());
		
		mockMvc.perform(get("/customer/show/" + id))
			.andExpect(status().isOk())
			.andExpect(view().name("customer/show"))
			.andExpect(model().attribute("customer", instanceOf(Customer.class)));
	}

	@Test
	public void testEdit() throws Exception {
		Integer id = 1;
		
		when(customerService.getById(id)).thenReturn(new Customer());
		
		mockMvc.perform(get("/customer/edit/" + id))
			.andExpect(status().isOk())
			.andExpect(view().name("customer/customerform"))
			.andExpect(model().attribute("customer", instanceOf(Customer.class)));
	}
	
	@Test
	public void testNewProduct() throws Exception {
		mockMvc.perform(get("/customer/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("customer/customerform"))
			.andExpect(model().attribute("customer", instanceOf(Customer.class)));
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		Integer id = 1;
		String firstName = "Bill";
		String lastName = "Smith";
		String emailAddress = "bsmith@nowhere.org";
		String phoneNumber = "443-555-1212";
		String addressLine1 = "100 Main St.";
		String addressLine2 = "Suite 333";
		String city = "Baltimore";
		String state = "MD";
		String zipCode = "12345";
		
		Customer returnCustomer = new Customer();
		returnCustomer.setId(id);
		returnCustomer.setFirstName(firstName);
		returnCustomer.setLastName(lastName);
		returnCustomer.setEmailAddress(emailAddress);
		returnCustomer.setPhoneNumber(phoneNumber);
		returnCustomer.setAddressLine1(addressLine1);
		returnCustomer.setAddressLine2(addressLine2);
		returnCustomer.setCity(city);
		returnCustomer.setState(state);
		returnCustomer.setZipCode(zipCode);
		
		when(customerService.saveOrUpdate(Matchers.<Customer>any()))
			.thenReturn(returnCustomer);
		
		mockMvc.perform(post("/customer")
			.param("id", id.toString())
			.param("firstName", firstName)
			.param("lastName", lastName)
			.param("emailAddress", emailAddress)
			.param("phoneNumber", phoneNumber)
			.param("addressLine1", addressLine1)
			.param("addressLine2", addressLine2)
			.param("city", city)
			.param("state", state)
			.param("zipCode", zipCode))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/customer/show/"+id))
				.andExpect(model().attribute("customer", instanceOf(Customer.class)))
				.andExpect(model().attribute("customer", hasProperty("id", is(id))))
				.andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
				.andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
				.andExpect(model().attribute("customer", hasProperty("emailAddress", is(emailAddress))))
				.andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
				.andExpect(model().attribute("customer", hasProperty("addressLine1", is(addressLine1))))
				.andExpect(model().attribute("customer", hasProperty("addressLine2", is(addressLine2))))
				.andExpect(model().attribute("customer", hasProperty("city", is(city))))
				.andExpect(model().attribute("customer", hasProperty("state", is(state))))
				.andExpect(model().attribute("customer", hasProperty("zipCode", is(zipCode))));
		
		//verify properties of bound object
		ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
		verify(customerService).saveOrUpdate(boundCustomer.capture());
		
		assertEquals(id, boundCustomer.getValue().getId());
		assertEquals(firstName, boundCustomer.getValue().getFirstName());
		assertEquals(lastName, boundCustomer.getValue().getLastName());
		assertEquals(emailAddress, boundCustomer.getValue().getEmailAddress());
		assertEquals(phoneNumber, boundCustomer.getValue().getPhoneNumber());
		assertEquals(addressLine1, boundCustomer.getValue().getAddressLine1());
		assertEquals(addressLine2, boundCustomer.getValue().getAddressLine2());
		assertEquals(city, boundCustomer.getValue().getCity());
		assertEquals(state, boundCustomer.getValue().getState());
		assertEquals(zipCode, boundCustomer.getValue().getZipCode());
	}
	
	@Test
	public void testDeleteById() throws Exception {
		Integer id = 1;
		
		mockMvc.perform(get("/customer/delete/"+id))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/customer/list"));
		
		verify(customerService, times(1)).deleteById(id);
	}
}
