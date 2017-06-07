package guru.springframework.services;

import guru.springframework.commands.CustomerForm;
import guru.springframework.domain.Customer;

public interface CustomerService extends CRUDService<Customer> {

	Customer saveOrUpdateCustomerform(CustomerForm customerFrom);
}
