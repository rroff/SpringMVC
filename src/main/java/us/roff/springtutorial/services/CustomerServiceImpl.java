package us.roff.springtutorial.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import us.roff.springtutorial.domain.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private Map<Integer, Customer> customers;
	
	public CustomerServiceImpl() {
		loadCustomers();
	}
	
	@Override
	public List<Customer> listAllCustomers() {
		return new ArrayList<>(customers.values());
	}
	
	@Override
	public Customer getCustomerById(Integer id) {
		return customers.get(id);
	}
	
	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		if (customer == null) {
			throw new RuntimeException("Customer cannot be null");
		} else {
			if (customer.getId() == null) {
				customer.setId(getNextKey());
			}
			customers.put(customer.getId(), customer);
		}
		return customer;
	}
	
	@Override
	public void deleteCustomerById(Integer id) {
		customers.remove(id);
	}
	
	private Integer getNextKey() {
		Integer nextKey;
		
		if (customers.isEmpty()) {
			nextKey = 1;
		} else {
			nextKey = Collections.max(customers.keySet()) + 1;
		}
		
		return nextKey;
	}
	
	private void loadCustomers() {
		customers = new HashMap<>();
		
		Customer customer = new Customer();
		customer.setId(1);
		customer.setLastName("Smith");
		customer.setFirstName("Bill");
		customer.setEmailAddress("bs@here");
		customer.setPhoneNumber("321-555-1212");
		customer.setAddressLine1("123 Main St.");
		customer.setAddressLine2("Suite 441");
		customer.setCity("Melbourne");
		customer.setState("FL");
		customer.setZipCode("32901");
		customers.put(customer.getId(), customer);
		
		customer = new Customer();
		customer.setId(2);
		customer.setLastName("Jones");
		customer.setFirstName("Betty");
		customer.setEmailAddress("bj@here");
		customer.setPhoneNumber("407-555-1212");
		customer.setAddressLine1("444 Orange Ave.");
		customer.setAddressLine2("");
		customer.setCity("Orlando");
		customer.setState("FL");
		customer.setZipCode("?????");
		customers.put(customer.getId(), customer);
	}
}
