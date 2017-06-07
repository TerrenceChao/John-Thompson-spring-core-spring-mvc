package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.commands.CustomerForm;
import guru.springframework.domain.Customer;

@Component
public class CustomerToCustomerForm implements Converter<Customer, CustomerForm> {

	@Override
	public CustomerForm convert(Customer customer) {
		
		CustomerForm customerForm = new CustomerForm();
		
        customerForm.setUserId(customer.getUser().getId());
        customerForm.setUserName(customer.getUser().getUsername());
        customerForm.setUserVersion(customer.getUser().getVersion());
        customerForm.setPassword(customer.getUser().getPassword());
        
		customerForm.setCustomerId(customer.getId());
        customerForm.setCustomerVersion(customer.getVersion());
        customerForm.setEmail(customer.getEmail());
        customerForm.setFirstName(customer.getFirstName());
        customerForm.setLastName(customer.getLastName());
        customerForm.setPhoneNumber(customer.getPhoneNumber());
		
		return customerForm;
	}

	
}
