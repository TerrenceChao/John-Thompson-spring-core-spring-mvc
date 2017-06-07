package guru.springframework.services.reposervices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.commands.CustomerForm;
import guru.springframework.converters.CustomerFormToCustomer;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.UserRepository;
import guru.springframework.services.CustomerService;

@Service
@Profile("springdatajpa")
public class CustomerServiceRepoDaoImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private CustomerFormToCustomer customerFormToCustomer; 

	@Override
	public List<Customer> listAll() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add); //fun with Java 8
		return customers;
	}

	@Override
	public Customer getById(Integer id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer saveOrUpdate(Customer domainObject) {
		return customerRepository.save(domainObject);
	}
	
	@Override
	public Customer saveOrUpdateCustomerform(CustomerForm customerFrom) {
		Customer newCustomer = customerFormToCustomer.convert(customerFrom);
		
		if (newCustomer.getUser().getId() != null) {
			Customer existingCustomer = getById(newCustomer.getId());
			newCustomer.getUser().setEnabled(existingCustomer.getUser().isEnabled());
		}
		
		return saveOrUpdate(newCustomer);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Customer customer = customerRepository.findOne(id);
		
		userRepository.delete(customer.getUser());
		customerRepository.delete(customer);
		
	}
}
