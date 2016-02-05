package us.roff.springtutorial.services;

import java.util.List;

import us.roff.springtutorial.domain.Customer;

public interface CustomerService {

	List<Customer> listAllCustomers();
	
	Customer getCustomerById(Integer id);
	
	Customer saveOrUpdateCustomer(Customer product);
	
	void deleteCustomerById(Integer id);
}
