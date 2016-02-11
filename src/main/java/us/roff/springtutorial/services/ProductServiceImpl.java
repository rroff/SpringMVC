package us.roff.springtutorial.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import us.roff.springtutorial.domain.DomainObject;
import us.roff.springtutorial.domain.Product;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {
	
	@Override
	public List<DomainObject> listAll() {
		return super.listAll();
	}
	
	@Override
	public Product getById(Integer id) {
		return (Product)super.getById(id);
	}
	
	@Override
	public Product saveOrUpdate(Product product) {
		return (Product)super.saveOrUpdate(product);
	}
	
	@Override
	public void deleteById(Integer id) {
		super.deleteById(id);
	}
	
	protected void loadDomainObjects() {
		domainMap = new HashMap<>();
		
		Product product = new Product();
		product.setId(1);
		product.setDescription("Product 1");
		product.setPrice(new BigDecimal("12.99"));
		product.setImageUrl("http://example.com/product1");
		domainMap.put(product.getId(), product);
		
		product = new Product();
		product.setId(2);
		product.setDescription("Product 2");
		product.setPrice(new BigDecimal("14.99"));
		product.setImageUrl("http://example.com/product2");
		domainMap.put(product.getId(), product);
		
		product = new Product();
		product.setId(3);
		product.setDescription("Product 3");
		product.setPrice(new BigDecimal("34.99"));
		product.setImageUrl("http://example.com/product3");
		domainMap.put(product.getId(), product);
		
		product = new Product();
		product.setId(4);
		product.setDescription("Product 4");
		product.setPrice(new BigDecimal("42.99"));
		product.setImageUrl("http://example.com/product4");
		domainMap.put(product.getId(), product);
		
		product = new Product();
		product.setId(5);
		product.setDescription("Product 5");
		product.setPrice(new BigDecimal("25.99"));
		product.setImageUrl("http://example.com/product5");
		domainMap.put(product.getId(), product);
	}
}
