package us.roff.springtutorial.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import us.roff.springtutorial.domain.Product;
import us.roff.springtutorial.services.ProductService;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		loadProducts();
	}
	
	public void loadProducts() {
		Product product = new Product();
		product.setId(1);
		product.setDescription("Product 1");
		product.setPrice(new BigDecimal("12.99"));
		product.setImageUrl("http://example.com/product1");
		productService.saveOrUpdate(product);
		
		product = new Product();
		product.setId(2);
		product.setDescription("Product 2");
		product.setPrice(new BigDecimal("14.99"));
		product.setImageUrl("http://example.com/product2");
		productService.saveOrUpdate(product);
		
		product = new Product();
		product.setId(3);
		product.setDescription("Product 3");
		product.setPrice(new BigDecimal("34.99"));
		product.setImageUrl("http://example.com/product3");
		productService.saveOrUpdate(product);
		
		product = new Product();
		product.setId(4);
		product.setDescription("Product 4");
		product.setPrice(new BigDecimal("42.99"));
		product.setImageUrl("http://example.com/product4");
		productService.saveOrUpdate(product);
		
		product = new Product();
		product.setId(5);
		product.setDescription("Product 5");
		product.setPrice(new BigDecimal("25.99"));
		product.setImageUrl("http://example.com/product5");
		productService.saveOrUpdate(product);
	}
}
