package us.roff.springtutorial.bootstrap;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import us.roff.springtutorial.domain.Address;
import us.roff.springtutorial.domain.Customer;
import us.roff.springtutorial.domain.Order;
import us.roff.springtutorial.domain.OrderDetail;
import us.roff.springtutorial.domain.Product;
import us.roff.springtutorial.domain.User;
import us.roff.springtutorial.domain.security.Role;
import us.roff.springtutorial.services.CustomerService;
import us.roff.springtutorial.services.OrderService;
import us.roff.springtutorial.services.ProductService;
import us.roff.springtutorial.services.RoleService;
import us.roff.springtutorial.services.UserService;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private CustomerService customerService;
	
	private ProductService productService;
	
	private UserService userService;
	
	private OrderService orderService;
	
	private RoleService roleService;
	
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		loadCustomers();
		loadProducts();
		loadUsers();
		loadOrders();
		
		loadRoles();
		assignUsersToDefaultRole();
	}
	
	public void loadCustomers() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setLastName("Smith");
		customer.setFirstName("Bill");
		customer.setEmailAddress("bs@here");
		customer.setPhoneNumber("321-555-1212");
		Address address = new Address();		
		address.setAddressLine1("123 Main St.");
		address.setAddressLine2("Suite 441");
		address.setCity("Melbourne");
		address.setState("FL");
		address.setZipCode("32901");
		customer.setBillingAddress(address);
		customerService.saveOrUpdate(customer);
		
		customer = new Customer();
		customer.setId(2);
		customer.setLastName("Jones");
		customer.setFirstName("Betty");
		customer.setEmailAddress("bj@here");
		customer.setPhoneNumber("407-555-1212");
		address = new Address();		
		address.setAddressLine1("444 Orange Ave.");
		address.setAddressLine2("");
		address.setCity("Orlando");
		address.setState("FL");
		address.setZipCode("33911");
		customer.setBillingAddress(address);
		customerService.saveOrUpdate(customer);
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
	
	public void loadUsers() {
		@SuppressWarnings("unchecked")
		List<Customer> customers = (List<Customer>)customerService.listAll();
		
		for (Customer customer : customers) {
			String username = (customer.getFirstName().charAt(0)
					          + customer.getLastName()).toLowerCase();
			String password = "Password1";
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setCustomer(customer);
			userService.saveOrUpdate(user);
		}
	}
	
	public void loadOrders() {
		@SuppressWarnings("unchecked")
		List<Customer> customers = (List<Customer>)customerService.listAll();
		
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>)productService.listAll();
		
		Order order = new Order();
		order.setCustomer(customers.get(0));
		Address address = new Address();
		address.setAddressLine1("123 Kettle Run Rd.");
		address.setCity("Atco");
		address.setState("NJ");
		address.setZipCode("08004");
		order.setShippingAddress(address);
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct(products.get(0));
		orderDetail.setQuantity(4);
		order.addOrderDetail(orderDetail);
		
		orderDetail = new OrderDetail();
		orderDetail.setProduct(products.get(4));
		orderDetail.setQuantity(1);
		order.addOrderDetail(orderDetail);
		
		orderService.saveOrUpdate(order);
		
		order = new Order();
		order.setCustomer(customers.get(0));
		address = new Address();
		address.setAddressLine1("123 Kettle Run Rd.");
		address.setCity("Atco");
		address.setState("NJ");
		address.setZipCode("08004");
		order.setShippingAddress(address);
		
		orderDetail = new OrderDetail();
		orderDetail.setProduct(products.get(2));
		orderDetail.setQuantity(10);
		order.addOrderDetail(orderDetail);
		
		orderService.saveOrUpdate(order);
		
		order = new Order();
		order.setCustomer(customers.get(1));
		address = new Address();
		address.setAddressLine1("445 Roosevelt Blvd.");
		address.setAddressLine2("Suite 5A");
		address.setCity("Philadelphia");
		address.setState("PA");
		address.setZipCode("19134");
		order.setShippingAddress(address);
		
		orderDetail = new OrderDetail();
		orderDetail.setProduct(products.get(0));
		orderDetail.setQuantity(5);
		order.addOrderDetail(orderDetail);
		
		orderDetail = new OrderDetail();
		orderDetail.setProduct(products.get(1));
		orderDetail.setQuantity(1);
		order.addOrderDetail(orderDetail);
		
		orderDetail = new OrderDetail();
		orderDetail.setProduct(products.get(3));
		orderDetail.setQuantity(1);
		order.addOrderDetail(orderDetail);
		
		orderService.saveOrUpdate(order);
	}
	
	public void loadRoles() {
		Role role = new Role();
		role.setRole("CUSTOMER");
		roleService.saveOrUpdate(role);
	}
	
	@SuppressWarnings("unchecked")
	public void assignUsersToDefaultRole() {
		List<Role> roles = (List<Role>)roleService.listAll();
		List<User> users = (List<User>)userService.listAll();
		
		for (Role role : roles) {
			if (role.getRole().equalsIgnoreCase("CUSTOMER")) {
				for (User user : users) {
					user.addRole(role);
					userService.saveOrUpdate(user);
				}
			}
		}
	}
}
