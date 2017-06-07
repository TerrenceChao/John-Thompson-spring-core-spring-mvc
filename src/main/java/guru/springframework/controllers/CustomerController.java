package guru.springframework.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.springframework.commands.CustomerForm;
import guru.springframework.commands.validators.CustomerFormValidator;
import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;

@Controller
@RequestMapping(value = "/customer/v1.0")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerFormValidator customerFormValidator; 
	
	//read all
	@RequestMapping("/list")
	public String listAll(Model model) {
		model.addAttribute("customers", this.customerService.listAll());
		return "customer/list";
	}
	
	//read
	@RequestMapping("/show/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", this.customerService.getById(id));
		return "customer/show";
	}
	
	//create
	@RequestMapping("/new")
	public String create(Model model) {
		model.addAttribute("customerForm", new CustomerForm());
		return "customer/customerform";
	}
	
	//update
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		Customer customer = this.customerService.getById(id);
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
		
		model.addAttribute("customerForm", customerForm);
		return "customer/customerform";
	}
	
	//confirm create/update
	@RequestMapping(value = "/confirmForm", method = RequestMethod.POST)
	public String saveOrUpdate(@Valid CustomerForm customerForm, BindingResult bindingResult) {
		
		this.customerFormValidator.validate(customerForm, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "customer/customerform";
		}
		
		Customer savedCustomer = this.customerService.saveOrUpdateCustomerform(customerForm);
		return "redirect:/customer/v1.0/show/" + savedCustomer.getId();
	}
	
	//delete
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.customerService.delete(id);
		return "redirect:/customer/v1.0/list";
	}
}
