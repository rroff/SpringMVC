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
}
