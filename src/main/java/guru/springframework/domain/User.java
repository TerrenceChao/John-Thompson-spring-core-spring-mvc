package guru.springframework.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class User extends AbstractDomainClass {

	private String username;
	
	@Transient // for transient and not part of table's field.
	private String password;	

	private String encryptedPassword;
	private boolean enabled = true;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Customer customer; 
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;
	
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		customer.setUser(this);
		this.customer = customer;
	}
	
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
