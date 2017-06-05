package guru.springframework.services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Customer;
import guru.springframework.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class CustomerServiceJpaDaoImplTest {
	
	@Autowired
	private CustomerService customerService;

	@Test
	public void testList() throws Exception { 
		List<Customer> customers = this.customerService.listAll();
		assert customers.size() == 3;
	}
	
	@Test
	public void testSaveOfCustomer() throws Exception {
		
		Customer customer = new Customer();
		User user = new User();
		user.setUsername("Lisa");
		user.setPassword("password");
		customer.setUser(user);
		
		Customer savedCustomer = this.customerService.saveOrUpdate(customer);
		assert savedCustomer.getUser().getId() != null;
	}
}
