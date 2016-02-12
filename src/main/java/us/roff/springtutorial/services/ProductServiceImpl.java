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
}
