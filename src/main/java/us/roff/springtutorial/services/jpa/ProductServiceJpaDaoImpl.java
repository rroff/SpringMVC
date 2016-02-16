package us.roff.springtutorial.services.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import us.roff.springtutorial.domain.Product;
import us.roff.springtutorial.services.ProductService;

@Service
@Profile("jpadao")
public class ProductServiceJpaDaoImpl extends AbstractJpaDaoService implements ProductService {

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
