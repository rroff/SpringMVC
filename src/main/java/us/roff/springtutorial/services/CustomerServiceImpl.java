package us.roff.springtutorial.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import us.roff.springtutorial.domain.Customer;
import us.roff.springtutorial.domain.DomainObject;

@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {
	
	@Override
	public List<DomainObject> listAll() {
		return super.listAll();
	}
	
	@Override
	public Customer getById(Integer id) {
		return (Customer)super.getById(id);
	}
	
	@Override
	public Customer saveOrUpdate(Customer customer) {
		return (Customer)super.saveOrUpdate(customer);
	}
	
	@Override
	public void deleteById(Integer id) {
		super.deleteById(id);
	}
	
	protected void loadDomainObjects() {
		domainMap = new HashMap<>();
		
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
		domainMap.put(customer.getId(), customer);
		
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
		domainMap.put(customer.getId(), customer);
	}
}
