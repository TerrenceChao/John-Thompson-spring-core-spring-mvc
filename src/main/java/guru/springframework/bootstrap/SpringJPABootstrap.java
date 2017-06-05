package guru.springframework.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Address;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.domain.User;
import guru.springframework.services.ProductService;
import guru.springframework.services.UserService;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private ProductService productService;
	private UserService userService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		loadProducts();
		loadUsersAndCustomers();
	}
	
	public void loadProducts() {
		Product p1 = new Product();
		p1.setDescription("Product JPA 1");
		p1.setPrice(new BigDecimal("11.99"));
		p1.setImageUrl("http://www.ex.com/product1");
		this.productService.saveOrUpdate(p1);
		
		Product p2 = new Product();
		p2.setDescription("Product JPA 2");
		p2.setPrice(new BigDecimal("23.99"));
		p2.setImageUrl("http://www.ex.com/product2");
		this.productService.saveOrUpdate(p2);
		
		Product p3 = new Product();
		p3.setDescription("Product JPA 3");
		p3.setPrice(new BigDecimal("33.99"));
		p3.setImageUrl("http://www.ex.com/product3");
		this.productService.saveOrUpdate(p3);
		
		Product p4 = new Product();
		p4.setDescription("Product JPA 4");
		p4.setPrice(new BigDecimal("44.99"));
		p4.setImageUrl("http://www.ex.com/product4");
		this.productService.saveOrUpdate(p4);
		
		Product p5 = new Product();
		p5.setDescription("Product JPA 5");
		p5.setPrice(new BigDecimal("55.99"));
		p5.setImageUrl("http://www.ex.com/product5");
		this.productService.saveOrUpdate(p5);
	}
	
    public void loadUsersAndCustomers() {
        User user1 = new User();
        user1.setUsername("mweston");
        user1.setPassword("password");

        Customer customer1 = new Customer();
        customer1.setFirstName("Micheal");
        customer1.setLastName("Weston");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("1 Main St");
        customer1.getBillingAddress().setCity("Miami");
        customer1.getBillingAddress().setState("Florida");
        customer1.getBillingAddress().setZipCode("33101");
        customer1.setEmail("micheal@burnnotice.com");
        customer1.setPhoneNumber("305.333.0101");
        user1.setCustomer(customer1);
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("fglenanne");
        user2.setPassword("password");

        Customer customer2 = new Customer();
        customer2.setFirstName("Fiona");
        customer2.setLastName("Glenanne");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLine1("1 Key Biscane Ave");
        customer2.getBillingAddress().setCity("Miami");
        customer2.getBillingAddress().setState("Florida");
        customer2.getBillingAddress().setZipCode("33101");
        customer2.setEmail("fiona@burnnotice.com");
        customer2.setPhoneNumber("305.323.0233");
        user2.setCustomer(customer2);
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("saxe");
        user3.setPassword("password");
        Customer customer3 = new Customer();
        customer3.setFirstName("Sam");
        customer3.setLastName("Axe");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLine1("1 Little Cuba Road");
        customer3.getBillingAddress().setCity("Miami");
        customer3.getBillingAddress().setState("Florida");
        customer3.getBillingAddress().setZipCode("33101");
        customer3.setEmail("sam@burnnotice.com");
        customer3.setPhoneNumber("305.426.9832");

        user3.setCustomer(customer3);
        userService.saveOrUpdate(user3);
    }

}
