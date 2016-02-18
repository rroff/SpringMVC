package us.roff.springtutorial.domain.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import us.roff.springtutorial.domain.AbstractDomainObject;
import us.roff.springtutorial.domain.User;

@Entity
public class Role extends AbstractDomainObject {

	private String role;
	
	@ManyToMany
	@JoinTable
	// NOTE: Defaults to
	//       @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "role_id"),
	//                  inverseJoinColumn = @joinColumn(name = "user_id"))
	private List<User> users = new ArrayList<>();
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		if (!users.contains(user)) {
			users.add(user);
		}
		
		if (!user.getRoles().contains(this)) {
			user.getRoles().add(this);
		}
	}
	
	public void removeUser(User user) {
		users.remove(user);
		user.getRoles().remove(this);
	}
}
