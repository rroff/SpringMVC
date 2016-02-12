package us.roff.springtutorial.services.map;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import us.roff.springtutorial.domain.DomainObject;
import us.roff.springtutorial.domain.User;
import us.roff.springtutorial.services.UserService;

@Service
@Profile("map")
public class UserServiceImpl extends AbstractMapService implements UserService {
	
	@Override
	public List<DomainObject> listAll() {
		return super.listAll();
	}
	
	@Override
	public User getById(Integer id) {
		return (User)super.getById(id);
	}
	
	@Override
	public User saveOrUpdate(User user) {
		return (User)super.saveOrUpdate(user);
	}
	
	@Override
	public void deleteById(Integer id) {
		super.deleteById(id);
	}
}
