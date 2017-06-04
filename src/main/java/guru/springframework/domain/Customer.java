package guru.springframework.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;

//@Entity
public class Customer extends AbstractDomainClass {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    
//    @Embedded
    private Address billingAddress;

//    @Embedded
	private Address shippingAddress;	
    
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
	public Address getBillingAddress() {
		return this.billingAddress;
	}
	
	public void setBillingAddress(Address address) {
		this.billingAddress = address;
	}
	
	public Address getShippingAddress() {
		return this.shippingAddress;
	}
	
	public void setShippingAddress(Address address) {
		this.shippingAddress = address;
	}
    
}
