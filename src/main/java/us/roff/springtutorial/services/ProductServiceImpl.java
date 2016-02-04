package us.roff.springtutorial.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import us.roff.springtutorial.domain.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	private Map<Integer, Product> products;
	
	public ProductServiceImpl() {
		loadProducts();
	}
	
	@Override
	public List<Product> listAllProducts() {
		return new ArrayList<>(products.values());
	}
	
	@Override
	public Product getProductById(Integer id) {
		return products.get(id);
	}
	
	private void loadProducts() {
		products = new HashMap<>();
		
		Product product = new Product();
		product.setId(1);
		product.setDescription("Product 1");
		product.setPrice(new BigDecimal("12.99"));
		product.setImageUrl("http://example.com/product1");
		products.put(product.getId(), product);
		
		product = new Product();
		product.setId(2);
		product.setDescription("Product 2");
		product.setPrice(new BigDecimal("14.99"));
		product.setImageUrl("http://example.com/product2");
		products.put(product.getId(), product);
		
		product = new Product();
		product.setId(3);
		product.setDescription("Product 3");
		product.setPrice(new BigDecimal("34.99"));
		product.setImageUrl("http://example.com/product3");
		products.put(product.getId(), product);
		
		product = new Product();
		product.setId(4);
		product.setDescription("Product 4");
		product.setPrice(new BigDecimal("42.99"));
		product.setImageUrl("http://example.com/product4");
		products.put(product.getId(), product);
		
		product = new Product();
		product.setId(5);
		product.setDescription("Product 5");
		product.setPrice(new BigDecimal("25.99"));
		product.setImageUrl("http://example.com/product5");
		products.put(product.getId(), product);
	}
}
