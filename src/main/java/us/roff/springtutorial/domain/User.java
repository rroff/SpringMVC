package us.roff.springtutorial.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import us.roff.springtutorial.domain.security.Role;

@Entity
public class User extends AbstractDomainObject {

	private String username;
	
	@Transient
	private String password;
	
	private String encryptedPassword;
	private Boolean enabled = true;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Customer customer;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Cart cart;
	
	@ManyToMany
	@JoinTable
	// NOTE: Defaults to
	//       @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_id"),
	//                  inverseJoinColumn = @joinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
		
		if (customer != null) {
			customer.setUser(this);
		}
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		if (!roles.contains(role)) {
			roles.add(role);
		}
		
		if (!role.getUsers().contains(this)) {
			role.getUsers().add(this);
		}
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
		role.getUsers().remove(this);
	}
}
