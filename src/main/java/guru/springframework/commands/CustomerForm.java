package guru.springframework.commands;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class CustomerForm {

	private Integer userId;
	private Integer userVersion;
	private Integer customerId;
	private Integer customerVersion;
	
	@NotEmpty
	@Size(min = 2, max = 13)
	private String userName;
	private String password;
	private String passwordConf;
	private String firstName;
	private String lastName;
	
	@NotEmpty
	@Email
	private String email;
	private String phoneNumber;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserVersion() {
		return userVersion;
	}
	public void setUserVersion(Integer userVersion) {
		this.userVersion = userVersion;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getCustomerVersion() {
		return customerVersion;
	}
	public void setCustomerVersion(Integer customerVersion) {
		this.customerVersion = customerVersion;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConf() {
		return passwordConf;
	}
	public void setPasswordConf(String passwordConf) {
		this.passwordConf = passwordConf;
	}
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
}
