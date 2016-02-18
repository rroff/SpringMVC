package us.roff.springtutorial.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer extends AbstractDomainObject {
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phoneNumber;
	
	@Embedded
	private Address billingAddress;
	
	@Embedded
	private Address shippingAddress;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Address getBillingAddress() {
		return billingAddress;
	}
	
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	public Address getShippingAddress() {
		return shippingAddress;
	}
	
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
		order.setCustomer(this);
	}
	
	public void removeOrder(Order order) {
		order.setCustomer(null);
		orders.remove(order);
	}
}
