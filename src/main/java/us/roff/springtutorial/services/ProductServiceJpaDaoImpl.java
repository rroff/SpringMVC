package us.roff.springtutorial.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import us.roff.springtutorial.domain.Product;

@Service
@Profile("jpadao")
public class ProductServiceJpaDaoImpl implements ProductService {

	private EntityManagerFactory emf;
	
	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<Product> listAll() {
		EntityManager em = emf.createEntityManager();
		
		return em.createQuery("FROM Product", Product.class).getResultList();
	}
	
	@Override
	public Product getById(Integer id) {
		EntityManager em = emf.createEntityManager();
		
		return em.find(Product.class, id);
	}
	
	@Override
	public Product saveOrUpdate(Product domainObject) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Product savedProduct = em.merge(domainObject);
		em.getTransaction().commit();
		
		return savedProduct;
	}
	
	@Override
	public void deleteById(Integer id) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.remove(em.find(Product.class, id));
		em.getTransaction().commit();
	}
}
