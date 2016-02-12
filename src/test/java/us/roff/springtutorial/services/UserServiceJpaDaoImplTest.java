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
import us.roff.springtutorial.domain.User;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
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
}
