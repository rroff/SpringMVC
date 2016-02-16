package us.roff.springtutorial.services;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import us.roff.springtutorial.config.JpaIntegrationConfig;
import us.roff.springtutorial.domain.Product;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class ProductServiceJpaDaoImplTest {
	
	private ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Test
	public void testListAll() throws Exception {
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>)productService.listAll();
		assertEquals(5, products.size());
	}
	
	@Test
	public void testGetById() throws Exception {
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>)productService.listAll();
		assertTrue(products.size() > 0);
		
		Product expectedProduct = products.get(0);
		
		Product actualProduct = productService.getById(expectedProduct.getId());
		assertNotNull(actualProduct);
		
		// Check fields
		assertEquals(expectedProduct.getId(), actualProduct.getId());
		assertEquals(expectedProduct.getVersion(), actualProduct.getVersion());
		assertEquals(expectedProduct.getDescription(), actualProduct.getDescription());
		assertEquals(expectedProduct.getPrice(), actualProduct.getPrice());
		assertEquals(expectedProduct.getImageUrl(), actualProduct.getImageUrl());
	}
	
	@Test
	@DirtiesContext
	public void testNew() throws Exception {
		String description = "testNew Description";
		BigDecimal price = new BigDecimal(59.95);
		String imageUrl = "testNewUrl";
		
		Product product = new Product();
		product.setDescription(description);
		product.setPrice(price);
		product.setImageUrl(imageUrl);
		
		Product savedProduct = productService.saveOrUpdate(product);
		assertEquals(description, savedProduct.getDescription());
		assertEquals(price, savedProduct.getPrice());
		assertEquals(imageUrl, savedProduct.getImageUrl());
	}
	
	@Test
	public void testUpdate() throws Exception {
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>)productService.listAll();
		assertTrue(products.size() > 0);
		
		Product product = products.get(0);
		
		String description = "testUpdate Description";
		BigDecimal price = new BigDecimal(132.32);
		String imageUrl = "testUpdateUrl";
		
		product.setDescription(description);
		product.setPrice(price);
		product.setImageUrl(imageUrl);
		
		Product savedProduct = productService.saveOrUpdate(product);
		
		assertEquals(product.getId(), savedProduct.getId());
		assertEquals(product.getVersion().intValue() + 1, savedProduct.getVersion().intValue());
		assertEquals(product.getDescription(), savedProduct.getDescription());
		assertEquals(product.getPrice(), savedProduct.getPrice());
		assertEquals(product.getImageUrl(), savedProduct.getImageUrl());
	}
	
	@Test
	public void testDeleteById() throws Exception {
		@SuppressWarnings("unchecked")
		List<Product> startProducts = (List<Product>)productService.listAll();
		assertTrue(startProducts.size() > 0);
		
		Integer idToDelete = startProducts.get(0).getId();
		productService.deleteById(idToDelete);
		
		@SuppressWarnings("unchecked")
		List<Product> endProducts = (List<Product>)productService.listAll();
		assertEquals(startProducts.size() - 1, endProducts.size());
		
		for (Product product : endProducts) {
			// Verify deleted id does not exist
			assertNotEquals(idToDelete, product.getId());
		}
	}
}
