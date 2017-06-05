package guru.springframework.services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Cart;
import guru.springframework.domain.CartDetail;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

	@Autowired
	private UserService userService;	
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void testSaveOfUser() throws Exception {		
		User user = new User();
		
		user.setUsername("someone");
		user.setPassword("password");
		
		User savedUser = this.userService.saveOrUpdate(user);
		
		assert savedUser.getId() != null;
		assert savedUser.getEncryptedPassword() != null;
		
		System.out.println("Encrypt Password:");
		System.out.println(savedUser.getEncryptedPassword());
		
		System.out.println("List Users:");
		for (User item : this.userService.listAll()) {
			System.out.println("User name: " + item.getUsername());
		}
	}
	
	@Test
	public void testSaveOfUserWithCustomer() throws Exception {
		
		User user = new User();
		user.setUsername("user 9527");
		user.setPassword("hisPassword");
		
		Customer customer = new Customer();
		customer.setFirstName("Linda");
		customer.setLastName("Smith");
		
		user.setCustomer(customer);
		
		User savedUser = this.userService.saveOrUpdate(user);
		
		assert savedUser.getId() != null;
		assert savedUser.getVersion() != null;
		assert savedUser.getCustomer() != null;
		assert savedUser.getCustomer().getId() != null;
	}
	
	@Test 
	public void testAddCartToUserWithCarDetails() throws Exception {
		User user = new User();
		
		user.setUsername("someone9527");
		user.setPassword("herPassword");
		
		user.setCart(new Cart());
		
		List<Product> storedProducts = this.productService.listAll();
		
		CartDetail cartItemOne = new CartDetail();
		cartItemOne.setProduct(storedProducts.get(0));
		user.getCart().addCartDetail(cartItemOne);
		
		CartDetail cartItemTwo = new CartDetail();
		cartItemTwo.setProduct(storedProducts.get(1));
		user.getCart().addCartDetail(cartItemTwo);
		
		User savedUser = this.userService.saveOrUpdate(user);
		
		assert savedUser.getId() != null;
		assert savedUser.getVersion() != null;
		assert savedUser.getCart() != null;
		assert savedUser.getCart().getId() != null;
	}
	
	@Test 
	public void testAddAndRemoveCartToUserWithCarDetails() throws Exception {
		User user = new User();
		
		user.setUsername("someone9527");
		user.setPassword("herPassword");
		
		user.setCart(new Cart());
		
		List<Product> storedProducts = this.productService.listAll();
		
		CartDetail cartItemOne = new CartDetail();
		cartItemOne.setProduct(storedProducts.get(0));
		user.getCart().addCartDetail(cartItemOne);
		
		CartDetail cartItemTwo = new CartDetail();
		cartItemTwo.setProduct(storedProducts.get(1));
		user.getCart().addCartDetail(cartItemTwo);
		
		User savedUser = this.userService.saveOrUpdate(user);		
		assert savedUser.getCart().getCarDetails().size() == 2;
		
		System.out.println("Compare mem address between \"cartItemOne\" and \"savedUser.getCart().getCarDetails().get(0)\"");
		System.out.println("cartItemOne: " + cartItemOne);
		System.out.println("CarDetails().get(0): " + savedUser.getCart().getCarDetails().get(0));
		
		savedUser.getCart().removeCartDetail(savedUser.getCart().getCarDetails().get(0));
		this.userService.saveOrUpdate(user);
		assert savedUser.getCart().getCarDetails().size() == 1;
		
	}
}
