package us.roff.springtutorial.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import us.roff.springtutorial.config.JpaIntegrationConfig;
import us.roff.springtutorial.domain.Cart;
import us.roff.springtutorial.domain.CartDetail;
import us.roff.springtutorial.domain.Customer;
import us.roff.springtutorial.domain.Product;
import us.roff.springtutorial.domain.User;

import static org.junit.Assert.*;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {
	
	private UserService userService;
	
	private ProductService productService;
		
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Test
	@DirtiesContext
	public void testNew() throws Exception {
		String username = "someuser";
		String password = "password";
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		User savedUser = userService.saveOrUpdate(user);
		assertNotNull(savedUser.getId());
		assertNotNull(savedUser.getEncryptedPassword());
		assertEquals(0, savedUser.getVersion().intValue());
		assertEquals(username, savedUser.getUsername());
	}
	
	@Test
	@DirtiesContext
	public void testNewUserWithCustomer() throws Exception {
		String username = "someuser";
		String password = "password";
		
		String firstName = "John";
		String lastName = "Smith";
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		
		user.setCustomer(customer);
		
		User savedUser = userService.saveOrUpdate(user);
		assertNotNull(savedUser.getId());
		assertEquals(0, savedUser.getVersion().intValue());
		assertNotNull(savedUser.getCustomer());
		assertNotNull(savedUser.getCustomer().getId());
	}
	
	@Test
	@DirtiesContext
	public void testNewUserWithCart() throws Exception {
		String username = "someuser";
		String password = "password";
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		user.setCart(new Cart());
		
		User savedUser = userService.saveOrUpdate(user);
		assertNotNull(savedUser.getId());
		assertEquals(0, savedUser.getVersion().intValue());
		assertNotNull(savedUser.getCart());
		assertNotNull(savedUser.getCart().getId());
	}
	
	@Test
	@DirtiesContext
	public void testNewUserWithCartDetails() throws Exception {
		String username = "someuser";
		String password = "password";
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		user.setCart(new Cart());
		
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>)productService.listAll();
		
		CartDetail cartItem = new CartDetail();
		cartItem.setProduct(products.get(0));
		user.getCart().addCartDetail(cartItem);
		
		cartItem = new CartDetail();
		cartItem.setProduct(products.get(1));
		user.getCart().addCartDetail(cartItem);
		
		User savedUser = userService.saveOrUpdate(user);
		assertNotNull(savedUser.getId());
		assertEquals(0, savedUser.getVersion().intValue());
		
		Cart cart = savedUser.getCart();
		assertNotNull(cart);
		assertEquals(2, cart.getCartDetails().size());
	}
	
	@Test
	@DirtiesContext
	public void testAddAndRemoveCartDetailsToUser() throws Exception {
		String username = "someuser";
		String password = "password";
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		user.setCart(new Cart());
		
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>)productService.listAll();
		
		CartDetail cartItem = new CartDetail();
		cartItem.setProduct(products.get(0));
		user.getCart().addCartDetail(cartItem);
		
		cartItem = new CartDetail();
		cartItem.setProduct(products.get(1));
		user.getCart().addCartDetail(cartItem);
		
		User savedUser = userService.saveOrUpdate(user);
		assertNotNull(savedUser.getId());
		assertNotNull(savedUser.getCart());
		assertEquals(2, savedUser.getCart().getCartDetails().size());
		
		savedUser.getCart().removeCartDetail(
				savedUser.getCart().getCartDetails().get(0));
		
		User modifiedUser = userService.saveOrUpdate(savedUser);
		assertNotNull(modifiedUser.getId());
		assertNotNull(modifiedUser.getCart());
		assertEquals(1, modifiedUser.getCart().getCartDetails().size());
	}
}
