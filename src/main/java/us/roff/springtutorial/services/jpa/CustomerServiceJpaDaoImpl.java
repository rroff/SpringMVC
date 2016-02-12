package us.roff.springtutorial.services.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import us.roff.springtutorial.domain.Customer;
import us.roff.springtutorial.services.CustomerService;
import us.roff.springtutorial.services.security.EncryptionService;

@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl extends AbstractJpaDaoService implements CustomerService {
	
	private EncryptionService encryptionService;
	
	@Autowired
	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}
	
	@Override
	public List<Customer> listAll() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("FROM Customer", Customer.class).getResultList();
	}
	
	@Override
	public Customer getById(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Customer.class, id);
	}
	
	@Override
	public Customer saveOrUpdate(Customer customer) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		if ((customer.getUser() != null) && (customer.getUser().getPassword() != null)) {
			customer.getUser().setEncryptedPassword(
					encryptionService.encryptString(customer.getUser().getPassword()));
		}
		
		Customer savedCustomer = em.merge(customer);
		em.getTransaction().commit();
		
		return savedCustomer;
	}
	
	@Override
	public void deleteById(Integer id) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.remove(em.find(Customer.class, id));
		em.getTransaction().commit();
	}
}
