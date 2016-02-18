package us.roff.springtutorial.services.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import us.roff.springtutorial.domain.Order;
import us.roff.springtutorial.services.OrderService;

@Service
@Profile("jpadao")
public class OrderServiceJpaDaoImpl extends AbstractJpaDaoService implements OrderService {

	@Override
	public List<Order> listAll() {
		EntityManager em = emf.createEntityManager();
		
		return em.createQuery("FROM Order", Order.class).getResultList();
	}
	
	@Override
	public Order getById(Integer id) {
		EntityManager em = emf.createEntityManager();
		
		return em.find(Order.class, id);
	}
	
	@Override
	public Order saveOrUpdate(Order domainObject) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Order savedOrder = em.merge(domainObject);
		em.getTransaction().commit();
		
		return savedOrder;
	}
	
	@Override
	public void deleteById(Integer id) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.remove(em.find(Order.class, id));
		em.getTransaction().commit();
	}
}
