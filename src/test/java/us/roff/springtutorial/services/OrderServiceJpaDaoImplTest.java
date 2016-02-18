package us.roff.springtutorial.services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import us.roff.springtutorial.config.JpaIntegrationConfig;
import us.roff.springtutorial.domain.Order;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class OrderServiceJpaDaoImplTest {
	
	private OrderService orderService;
	
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Test
	public void testListAll() throws Exception {
		@SuppressWarnings("unchecked")
		List<Order> products = (List<Order>)orderService.listAll();
		assertEquals(3, products.size());
	}
	
//	@Test
//	public void testGetById() throws Exception {
//		@SuppressWarnings("unchecked")
//		List<Product> products = (List<Product>)orderService.listAll();
//		assertTrue(products.size() > 0);
//		
//		Product expectedProduct = products.get(0);
//		
//		Product actualProduct = orderService.getById(expectedProduct.getId());
//		assertNotNull(actualProduct);
//		
//		// Check fields
//		assertEquals(expectedProduct.getId(), actualProduct.getId());
//		assertEquals(expectedProduct.getVersion(), actualProduct.getVersion());
//		assertEquals(expectedProduct.getDescription(), actualProduct.getDescription());
//		assertEquals(expectedProduct.getPrice(), actualProduct.getPrice());
//		assertEquals(expectedProduct.getImageUrl(), actualProduct.getImageUrl());
//	}
	
//	@Test
//	@DirtiesContext
//	public void testNew() throws Exception {
//		String description = "testNew Description";
//		BigDecimal price = new BigDecimal(59.95);
//		String imageUrl = "testNewUrl";
//		
//		Product product = new Product();
//		product.setDescription(description);
//		product.setPrice(price);
//		product.setImageUrl(imageUrl);
//		
//		Product savedProduct = orderService.saveOrUpdate(product);
//		assertEquals(description, savedProduct.getDescription());
//		assertEquals(price, savedProduct.getPrice());
//		assertEquals(imageUrl, savedProduct.getImageUrl());
//	}
	
//	@Test
//	public void testUpdate() throws Exception {
//		@SuppressWarnings("unchecked")
//		List<Order> orders = (List<Order>)orderService.listAll();
//		assertTrue(orders.size() > 0);
//		
//		Order order = orders.get(0);
//		
//		order.setCustomer(customer);
//		order.setShippingAddress(shippingAddress);
//		order.setOrderDetails(orderDetails);
//		
//		Order savedOrder = orderService.saveOrUpdate(order);
//		
//		assertEquals(order.getId(), savedOrder.getId());
//		assertEquals(order.getVersion().intValue() + 1, savedOrder.getVersion().intValue());
//		
//		assertEquals(order.getCustomer().getId(), savedOrder.getCustomer().getId());
//		
//		assertEquals(order.getShippingAddress().getAddressLine1(), savedOrder.getShippingAddress().getAddressLine1());
//		assertEquals(order.getShippingAddress().getAddressLine2(), savedOrder.getShippingAddress().getAddressLine2());
//		assertEquals(order.getShippingAddress().getCity(), savedOrder.getShippingAddress().getCity());
//		assertEquals(order.getShippingAddress().getState(), savedOrder.getShippingAddress().getState());
//		assertEquals(order.getShippingAddress().getZipCode(), savedOrder.getShippingAddress().getZipCode());
//		
//		assertEquals(order.getOrderDetails().size(), savedOrder.getOrderDetails().size());
//	}
	
	@Test
	public void testDeleteById() throws Exception {
		@SuppressWarnings("unchecked")
		List<Order> startOrders = (List<Order>)orderService.listAll();
		assertTrue(startOrders.size() > 0);
		
		Integer idToDelete = startOrders.get(0).getId();
		orderService.deleteById(idToDelete);
		
		@SuppressWarnings("unchecked")
		List<Order> endOrders = (List<Order>)orderService.listAll();
		assertEquals(startOrders.size() - 1, endOrders.size());
		
		for (Order order : endOrders) {
			// Verify deleted id does not exist
			assertNotEquals(idToDelete, order.getId());
		}
	}
}
