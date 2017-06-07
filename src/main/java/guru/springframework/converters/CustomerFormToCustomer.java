package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.commands.CustomerForm;
import guru.springframework.domain.Address;
import guru.springframework.domain.Customer;
import guru.springframework.domain.User;

@Component
public class CustomerFormToCustomer implements Converter<CustomerForm, Customer> {

	@Override
	public Customer convert(CustomerForm customerForm) {
		
		Customer customer = new Customer();
		customer.setUser(new User());
		customer.setBillingAddress(new Address());
		customer.setShippingAddress(new Address());
		
		customer.getUser().setId(customerForm.getUserId());
		customer.getUser().setVersion(customerForm.getUserVersion());
		customer.getUser().setUsername(customerForm.getUserName());
		customer.getUser().setPassword(customerForm.getPassword());
		
		customer.setId(customerForm.getCustomerId());
		customer.setVersion(customerForm.getCustomerVersion());
		customer.setFirstName(customerForm.getFirstName());
		customer.setLastName(customerForm.getLastName());
		customer.setEmail(customerForm.getEmail());
		customer.setPhoneNumber(customerForm.getPhoneNumber());
		return customer;
	}

}
